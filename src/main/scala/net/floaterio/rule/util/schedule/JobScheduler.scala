package net.floaterio.rule.util.schedule

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/22
 * Time: 1:12
 * To change this template use File | Settings | File Templates.
 */

import scala.actors.Actor
import org.quartz.impl.StdSchedulerFactory
import org.quartz._
import ScheduleUtil._
import org.apache.commons.logging.LogFactory
import org.quartz.JobKey._
import org.quartz.impl.matchers.KeyMatcher._
import java.util.concurrent.TimeUnit
import net.floaterio.rule.core.command.OnSchedule

trait JobScheduler {

  def addIntervalJob(name: String, group: String, receiver: Actor, span: Int, timeUnit: TimeUnit)

  def addCronJob(name: String, group: String, receiver: Actor, cron: String)

  def pause(name: String, group: String)

  def resume(name: String, group: String)

  def remove(name: String, group: String)
}

class JobSchedulerImpl extends JobScheduler{

  val logger = LogFactory.getLog(getClass)

  lazy val sche = {
    val s = StdSchedulerFactory.getDefaultScheduler
    s.start()
    s
  }

  def addCronJob(name: String, group: String, receiver: Actor, cron: String) {
    val trigger = createCronTrigger(name, group, cron)
    addJob(name, group, receiver, trigger)
  }

  def addIntervalJob(name: String, group: String, receiver: Actor, span: Int, timeUnit: TimeUnit) {
    val trigger = createIntervalTrigger(name, group, span, timeUnit)
    addJob(name, group, receiver, trigger)
  }

  def addJob(name: String, group: String, receiver: Actor, trigger: Trigger) = {

    logger.info("register new job %s, %s".format(name, group))

    sche.getListenerManager().addJobListener(new SubmitToActorJobListener(name, group, receiver),
      keyEquals(jobKey(name, group)))

    sche.scheduleJob(createJob(name, group), trigger)

  }

  def pause(name: String, group: String) = {
    logger.info("pause job name=%s group=%s", name, group)
    sche.pauseJob(jobKey(name, group))
  }

  def resume(name: String, group: String) = {
    logger.info("resume job name=%s group=%s", name, group)
    sche.resumeJob(jobKey(name, group))
  }

  def remove(name: String, group: String) = {
    logger.info("remove job name=%s group=%s", name, group)
    sche.deleteJob(jobKey(name, group))
  }

}

case class JobExecuting(name: String, group: String)

class SubmitToActorJobListener(name: String, group: String, actor: Actor) extends JobListener {

  val logger = LogFactory.getLog(getClass)

  def getName: String = group + ":" + name

  def jobToBeExecuted(context: JobExecutionContext) {
    logger.info("job to be executed. name=%s group=%s".format(name, group))
    actor ! OnSchedule()
  }

  def jobExecutionVetoed(context: JobExecutionContext) {
  }

  def jobWasExecuted(context: JobExecutionContext, jobException: JobExecutionException) {
  }
}

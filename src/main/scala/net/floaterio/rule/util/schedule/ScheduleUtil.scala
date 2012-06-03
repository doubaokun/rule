package net.floaterio.rule.util.schedule

import java.util.concurrent.TimeUnit
import org.quartz.JobBuilder._
import org.quartz.TriggerBuilder._
import org.quartz.SimpleScheduleBuilder._
import org.quartz.CronScheduleBuilder._
import org.quartz.{JobExecutionContext, Job}

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/24
 * Time: 18:26
 * To change this template use File | Settings | File Templates.
 */

object ScheduleUtil {

  def createJob(name: String, group: String) = {
    newJob(classOf[EmptyJob])
      .withIdentity(name, group)
      .usingJobData("id", group + ":" + name)
      .build()
  }

  def createIntervalTrigger(name: String, group: String, span: Int, timeUnit: TimeUnit) = {

    val trigger = newTrigger().withIdentity(name, group).startNow()
    val builder = simpleSchedule()

    if (timeUnit == TimeUnit.HOURS) {
      builder.withIntervalInHours(span)
    } else if (timeUnit == TimeUnit.MINUTES) {
      builder.withIntervalInMinutes(span)
    } else if (timeUnit == TimeUnit.SECONDS) {
      builder.withIntervalInSeconds(span)
    } else {
      throw new IllegalArgumentException("millisecond not supported.")
    }
    trigger.withSchedule(builder.repeatForever())
    trigger.build()
  }

  def createCronTrigger(name: String, group: String, cron: String) = {
    val trigger = newTrigger().withIdentity(name, group).startNow()
    trigger.withSchedule(cronSchedule(cron))
    trigger.build()
  }
}

class EmptyJob extends Job {
  def execute(context: JobExecutionContext) {}
}

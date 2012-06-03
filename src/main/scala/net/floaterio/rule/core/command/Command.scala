package net.floaterio.rule.core.command

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/01
 * Time: 0:29
 * To change this template use File | Settings | File Templates.
 */

trait Command {
  val name : String
}

case class Start() extends Command {
  val name = "common.start"
}

case class Pause() extends Command {
  val name = "common.pause"
}

case class Resume() extends Command {
  val name = "common.resume"
}

case class Stop() extends Command {
  val name = "common.stop"
}

case class OnSchedule() extends Command {
  val name = "schedule.onSchedule"
}


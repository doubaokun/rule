package net.floaterio.rule.filter

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/01
 * Time: 2:40
 * To change this template use File | Settings | File Templates.
 */

trait Filter {

  def filterName() :String
  def pause()
  def resume()
  def isResume(): Boolean

}

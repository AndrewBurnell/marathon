package mesosphere.marathon.tasks

import mesosphere.marathon.core.instance.Instance
import org.apache.mesos.Protos.TaskID
import org.scalatest.{ FunSuite, Matchers }
import mesosphere.marathon.state.PathId._

class TaskIdTest extends FunSuite with Matchers {

  test("AppIds can be converted to TaskIds and back to AppIds") {
    val appId = "/test/foo/bla/rest".toPath
    val taskId = Instance.Id.forRunSpec(appId)
    taskId.runSpecId should equal(appId)
  }

  test("Old TaskIds can be converted") {
    val taskId = Instance.Id(TaskID.newBuilder().setValue("app_682ebe64-0771-11e4-b05d-e0f84720c54e").build)
    taskId.runSpecId should equal("app".toRootPath)
  }

  test("Old TaskIds can be converted even if they have dots in them") {
    val taskId = Instance.Id(TaskID.newBuilder().setValue("app.foo.bar_682ebe64-0771-11e4-b05d-e0f84720c54e").build)
    taskId.runSpecId should equal("app.foo.bar".toRootPath)
  }

  test("Old TaskIds can be converted even if they have underscores in them") {
    val taskId = Instance.Id(TaskID.newBuilder().setValue("app_foo_bar_0-12345678").build)
    taskId.runSpecId should equal("/app/foo/bar".toRootPath)
  }
}

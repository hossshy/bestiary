package strnet

import java.io.{ File, FileInputStream }
import java.util.Properties

import scalafx.Includes._
import scalafx.application.JFXApp.PrimaryStage
import scalafx.application.{ JFXApp, Platform }
import scalafx.scene.Scene
import scalafx.scene.image.Image
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.stage.WindowEvent

object Gui extends JFXApp {
  val commonProp = new Properties
  commonProp.load(new FileInputStream(new File("conf" + File.separator + "common.conf")))

  val dir = new File(commonProp.getProperty("directories.path"))
  val command = commonProp.getProperty("exec.path")

  val defaultImg = new Image(new File("conf" + File.separator + "no.jpg").toURI().toString)

  val rootPane = new BorderPane()
  val listPane = new FlowPane(10, 10)
  val scrollPane = new ScrollPane()
  scrollPane.setContent(listPane)
  listPane.prefWidth.bind(scrollPane.width)
  listPane.prefHeight.bind(scrollPane.height)
  scrollPane.styleClass.append("base")


  rootPane.setCenter(scrollPane)

  def scanFiles(dir: File): Unit = {
    val thumbnails = dir.listFiles.filter(f => f.isFile && !f.getName.matches(".*\\.jpg"))
    thumbnails.foreach { f => listPane.children.add(new FileInfo(f, command, ".jpg", defaultImg)) }
  }

  stage = new PrimaryStage {
    title = "Bestiary"
    scene = new Scene(rootPane, 1280, 768) {
      stylesheets.add("/strnet/main.css")
    }
    onCloseRequest = (we: WindowEvent) => Platform.exit()
  }

  println(dir + ":" + dir.exists())
  if ( dir.exists() && dir.isDirectory() ) {
    scanFiles(dir)
  }
}

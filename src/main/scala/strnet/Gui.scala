package strnet

import java.io.{ File, FileInputStream }
import java.util.Properties
import scala.collection.JavaConversions._
import scalafx.Includes._
import scalafx.application.JFXApp.PrimaryStage
import scalafx.application.{ JFXApp, Platform }
import scalafx.scene.Scene
import scalafx.scene.image.{ Image, ImageView }
import scalafx.scene.layout.{ BorderPane, HBox }
import scalafx.stage.WindowEvent

object Gui extends JFXApp {
  val rootPane = new BorderPane()
  val listPane = new HBox()
  rootPane.setCenter(listPane)


  def makeImageView(img: Image): ImageView = {
    val imageView = new ImageView(img)
    if (img.getWidth > 120) {
      imageView.fitWidth = 120
    }
    if (img.getHeight > 180) {
      imageView.fitHeight = 180
    }
    imageView
  }

  def scanThumbnails(dir: File): Unit = {
    val thumbnails = dir.listFiles.filter(f => f.isFile && f.getName.matches(".*\\.jpg"))
    thumbnails.foreach { f => listPane.children.add(makeImageView(new Image(f.toURI().toString))) }
  }

  stage = new PrimaryStage {
    title = "Bestiary"
    scene = new Scene(rootPane, 1280, 768) {
    }
    onCloseRequest = (we: WindowEvent) => Platform.exit()
  }

  val commonProp = new Properties
  commonProp.load(new FileInputStream(new File("conf" + File.separator + "common.conf")))

  val dir = new File(commonProp.getProperty("directories-path"))
  println(dir)
  println(dir.exists())
  println(dir.isDirectory())
  if ( dir.exists() && dir.isDirectory() ) {
    scanThumbnails(dir)
  }
}

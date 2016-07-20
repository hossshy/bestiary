package strnet

import java.io.File
import scala.sys.process._
import scalafx.Includes._
import org.apache.commons.io.FilenameUtils
import scalafx.scene.control.Label
import scalafx.scene.image.{ Image, ImageView }
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.VBox

class FileInfo(
  file: File,
  command: String,
  thumbnailExtension: String,
  defaultImg: Image)
    extends VBox {

  val baseName = FilenameUtils.getBaseName(file.getName)
  val imgFile = new File(FilenameUtils.removeExtension(file.getPath()) + thumbnailExtension)
  val img = if ( imgFile.exists() ) {
    new Image(imgFile.toURI().toString)
  } else {
    defaultImg
  }

  val imageView = new ImageView(img) {
    preserveRatio = true
    smooth = false
  }

  if (img.getWidth > 120) {
    imageView.fitWidth = 120
  }
  if (img.getHeight > 120) {
    imageView.fitHeight = 120
  }

  onMouseClicked = (e: MouseEvent) => Seq(command, file.getPath).!

  styleClass.append("thumbnail")
  children.addAll(
    imageView,
    new Label(baseName))
  alignment = scalafx.geometry.Pos.TopCenter
}

import java.awt.{Graphics, Point}
class Slider (width:Int, percentage:Double) extends BarObject(width, percentage) {

  var circlePosition = 0
  val sliderRadius = _height/2

  override def draw(g: Graphics): Unit = {
    circlePosition = xPosition + (_width * _percentage).toInt
    g.drawRoundRect(xPosition, yPosition+3*(_height/8), _width, _height/4, _height/4, _height/4)
    g.fillOval(circlePosition - sliderRadius, yPosition, sliderRadius * 2, sliderRadius * 2)
    super.draw(g)
  }

  override def establishSelectionPoint(mouse: Point): Unit = {
    super.establishSelectionPoint(mouse)
    if(pointInRange(circlePosition - sliderRadius, circlePosition + sliderRadius, yPosition, yPosition + _height, mouse)) {
      selectionPoint = 5
    }
  }

  override def scale(xAmount: Int, yAmount: Int): Unit = {
    if(selectionPoint == 5) {
      _percentage = ((_width * _percentage) + xAmount) / _width
      if(_percentage < 0) {
        _percentage = 0
      }
      if(_percentage > 1)
        _percentage = 1
    }
    super.scale(xAmount, yAmount)
  }
}

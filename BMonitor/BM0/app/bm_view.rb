class BMView < UIView

	def initWithFrame(frame)
		if super(frame)
			@meter = []
		end
		self
	end

	def drawRect(rect)
		UIColor.brownColor.set
		c = UIGraphicsGetCurrentContext()
		CGContextSetLineWidth(c, 1)

		0.upto(@meter.size-1) do |x|
			xpos = x
			CGContextMoveToPoint(c, xpos, 0)
			CGContextAddLineToPoint(c, xpos, @meter[x])
			CGContextStrokePath(c)
		end
	end

	def add_to_meter(value)
		@meter << value
		if @meter.size > frame.size.width 
			@meter.shift
		end
	end

end

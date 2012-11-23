class Koma1ListViewController < UIViewController

	def viewDidLoad
		view.backgroundColor = UIColor.blueColor
        # self.title = "Second Nav View"

        # @third_view = ThirdNavController.new 
        # self.navigationController.pushViewController(@third_view, animated:'YES')

        # current_controllers = self.navigationController.viewControllers
        @my_button = UIButton.buttonWithType(UIButtonTypeRoundedRect)
        @my_button.frame = [[110,150],[100,37]]
        @my_button.setTitle("Press Me", forState:UIControlStateNormal)
        @my_button.setTitle("Impressive!", forState:UIControlStateHighlighted)
        # events
        @my_button.addTarget(self, action:'buttonIsPressed', forControlEvents:UIControlEventTouchDown)
        @my_button.addTarget(self, action:'buttonIsTapped', forControlEvents:UIControlEventTouchUpInside)
        view.addSubview(@my_button)

        # refer to Programming iOS 5 chapter 30
        @sess = AVCaptureSession.new
        cam = AVCaptureDevice.defaultDeviceWithMediaType(AVMediaTypeVideo)
        input = AVCaptureDeviceInput.deviceInputWithDevice(cam, error:nil)
        @sess.addInput(input)
        lay = AVCaptureVideoPreviewLayer.alloc.initWithSession(@sess)
        lay.frame = [[10, 30], [300, 300]]
        view.layer.addSublayer(lay)
        @sess.startRunning

        # @label = UILabel.new
        # @label.text = "viewController Count: "
        # @label.frame = [[50,50],[350,50]]
        # view.addSubview(@label)
        @image_counter = 0
end

def buttonIsPressed
  NSLog("Button was pressed down")
  self.view.removeFromSuperview
  # self.selectPhotos
end

def buttonIsTapped
  NSLog("Button was tapped")
end

# def selectPhotos
#     imagePicker = UIImagePickerController.alloc.init
#     imagePicker.delegate = self
#     # imagePicker.sourceType = UIImagePickerControllerSourceTypePhotoLibrary
#     imagePicker.mediaTypes = NSArray.arrayWithObject(KUTTypeImage)
#     self.presentModalViewController(imagePicker, animated:true)
# end

# def imagePickerController(picker, didFinishPickingImage:image, editingInfo:editingInfo)
#     @image_counter = @image_counter + 1
#     puts @image_counter
# end


end

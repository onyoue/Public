class Koma1ViewController < UIViewController

	def loadView
		@imageView = UIImageView.alloc.init
		self.view = @imageView
		self.view.backgroundColor = UIColor.blueColor
		self.view.userInteractionEnabled = true

		@toolBar = UIToolbar.alloc.initWithFrame(CGRectMake(0, 416, 320, 44))
		self.view.addSubview(@toolBar)
		items = []
		[['Add Picture', 'addPicture'],
			['Play', 'play'],
			['Stop', 'stop']].each do | buttonItem |

			barButtonItem = UIBarButtonItem.alloc.initWithTitle(buttonItem[0],
																style:UIBarButtonItemStyleBordered,
																target:self,
																action:buttonItem[1])
			items << barButtonItem
		end

		@toolBar.items = items
	end

	def addPicture
		imagePickerController = UIImagePickerController.alloc.init
		imagePickerController.delegate = self
		self.presentModalViewController(imagePickerController, animated:true)
	end

	def imagePickerController(picker, didFinishPickingImage:image, editingInfo:editingInfo)
		animationImages = @imageView.animationImages
		if animationImages
			images = NSMutableArray.arrayWithArray(animationImages)
			images.addObject(image)
		else
			images = NSMutableArray.arrayWithObject(image)
		end
		@imageView.image = image
		@imageView.animationImages = images
		picker.dismissModalViewControllerAnimated(true)
	end

	def play
		@imageView.setAnimationDuration(0.5)
		@imageView.startAnimating
	end

	def stop
		@imageView.stopAnimating
	end

end


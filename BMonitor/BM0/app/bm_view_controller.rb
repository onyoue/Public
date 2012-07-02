class BMViewController < UIViewController

	def loadView
		@bmView = BMView.alloc.init
		self.view = @bmView
		self.view.backgroundColor = UIColor.blueColor
		self.view.userInteractionEnabled = true

		@toolBar = UIToolbar.alloc.initWithFrame(CGRectMake(0, 416, 320, 44))
		self.view.addSubview(@toolBar)
		items = []
		[['Start', 'start'], ['Stop', 'stop']].each do | buttonItem |

			barButtonItem = UIBarButtonItem.alloc.initWithTitle(buttonItem[0],
																style:UIBarButtonItemStyleBordered,
																target:self,
																action:buttonItem[1])
			items << barButtonItem
		end

		@toolBar.items = items
	end

	def levelTimerCallback
		#puts 'hello'
		@recorder.updateMeters
		@bmView.add_to_meter( - @recorder.averagePowerForChannel(0))
		@bmView.setNeedsDisplay()
		# puts "Average input: #{@recorder.averagePowerForChannel(0)}"
	end

	def viewDidLoad
		#super.viewDidLoad

		url = NSURL.fileURLWithPath("dev/null")
		settings = { AVSampleRateKey => 44100.0,
					 AVFormatIDKey   => KAudioFormatAppleLossless,
					 AVNumberOfChannelsKey => 1,
					 AVEncoderAudioQualityKey => AVAudioQualityMax
		}

		perror = Pointer.new(:object)
		@recorder = AVAudioRecorder.alloc.initWithURL(url, settings:settings, error:perror)
		if @recorder
			@recorder.prepareToRecord
			@recorder.meteringEnabled = true
			@recorder.record
		else
			puts "hello"
			error = perror[0]
			$stderr.puts "Error #{error.description}"
			#puts perror.description
		end
	end

	def start
		@levelTimer = NSTimer.scheduledTimerWithTimeInterval(0.03, target:self, selector:'levelTimerCallback', userInfo:nil, repeats:true)
	end

	def stop
		@levelTimer.invalidate()
	end

end


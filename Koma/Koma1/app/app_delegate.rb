class AppDelegate
  def application(application, didFinishLaunchingWithOptions:launchOptions)
	  @window = UIWindow.alloc.initWithFrame(UIScreen.mainScreen.bounds)
	  @window.rootViewController = Koma1ViewController.alloc.init
	  @window.makeKeyAndVisible
    true
  end
end

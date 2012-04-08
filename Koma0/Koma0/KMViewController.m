//
//  KMViewController.m
//  Koma0
//
//  Created by 尾上 耕一 on 12/04/04.
//  Copyright (c) 2012年 Koichi Onoue. All rights reserved.
//

#import "KMViewController.h"

@interface KMViewController ()

@end

@implementation KMViewController
@synthesize imageView;
//@synthesize images;

- (IBAction)addPicture:(id)sender {
	UIImagePickerController* imagePickerController = [[UIImagePickerController alloc] init];
	imagePickerController.delegate = self;
	[self presentModalViewController:imagePickerController animated:YES];
}

- (void) imagePickerController:(UIImagePickerController *)picker didFinishPickingImage:(UIImage *)image editingInfo:(NSDictionary *)editingInfo {
	
	NSMutableArray* images;
	NSArray* animationImages = [imageView animationImages];
	if (nil == animationImages) {
		images = [NSMutableArray arrayWithObject:image];
	} else {
		images = [NSMutableArray arrayWithArray:animationImages];
		[images addObject:image];
	}
//	[images addObject:image];
	[imageView setImage:image];
	[imageView setAnimationImages:images];
	[picker dismissModalViewControllerAnimated:YES];
}

- (IBAction)play:(id)sender {
	[imageView setAnimationDuration:0.5];
	[imageView startAnimating];
}

- (IBAction)stop:(id)sender {
	[imageView stopAnimating];
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
}

- (void)viewDidUnload
{
	[self setImageView:nil];
    [super viewDidUnload];
    // Release any retained subviews of the main view.
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
	if ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPhone) {
	    return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);
	} else {
	    return YES;
	}
}

@end

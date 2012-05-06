//
//  RensyaViewController.m
//  Rensya
//
//  Created by 尾上 耕一 on 12/04/16.
//  Copyright (c) 2012年 Koichi Onoue. All rights reserved.
//

#import "RensyaViewController.h"

@interface RensyaViewController ()

@end

@implementation RensyaViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
}

- (void)viewDidUnload
{
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

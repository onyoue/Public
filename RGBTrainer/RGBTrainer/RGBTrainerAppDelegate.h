//
//  RGBTrainerAppDelegate.h
//  RGBTrainer
//
//  Created by 尾上 耕一 on 11/08/24.
//  Copyright 2011年 Koichi Onoue. All rights reserved.
//

#import <UIKit/UIKit.h>

@class RGBTrainerViewController;

@interface RGBTrainerAppDelegate : NSObject <UIApplicationDelegate>

@property (nonatomic, retain) IBOutlet UIWindow *window;

@property (nonatomic, retain) IBOutlet RGBTrainerViewController *viewController;

@end

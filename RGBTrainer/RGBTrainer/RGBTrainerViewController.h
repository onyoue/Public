//
//  RGBTrainerViewController.h
//  RGBTrainer
//
//  Created by 尾上 耕一 on 11/08/24.
//  Copyright 2011年 Koichi Onoue. All rights reserved.
//

#import <UIKit/UIKit.h>

#define kFillingComponent 0
#define kBreadComonent 1

@interface RGBTrainerViewController : UIViewController
<UIPickerViewDelegate, UIPickerViewDataSource> {
	UIPickerView* picker;
	NSMutableArray* pickerData;
	UIView* colorView;
	UIView* questionView;
	float _red;
	float _green;
	float _blue;
	UITextField* textField0;
	UITextField* textField1;
	UITextField* textField2;
	UITextField* textField3;
	UITextField* textField4;
	UITextField* textField5;
	UITextField* answerTextField0;
	UITextField* answerTextField1;
	UITextField* answerTextField2;
}

@property (nonatomic, retain) IBOutlet UIPickerView* picker;
@property (nonatomic, retain) IBOutlet UIView* colorView;
@property (nonatomic, retain) IBOutlet UIView* questionView;
@property (nonatomic, retain) NSMutableArray* pickerData;
@property (nonatomic, retain) IBOutlet UITextField* textField0;
@property (nonatomic, retain) IBOutlet UITextField* textField1;
@property (nonatomic, retain) IBOutlet UITextField* textField2;
@property (nonatomic, retain) IBOutlet UITextField* textField3;
@property (nonatomic, retain) IBOutlet UITextField* textField4;
@property (nonatomic, retain) IBOutlet UITextField* textField5;
@property (nonatomic, retain) IBOutlet UITextField* answerTextField0;
@property (nonatomic, retain) IBOutlet UITextField* answerTextField1;
@property (nonatomic, retain) IBOutlet UITextField* answerTextField2;

- (IBAction)nextButtonPressed;
- (IBAction)answerButtonPressed;
@end

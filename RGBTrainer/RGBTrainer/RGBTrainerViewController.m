//
//  RGBTrainerViewController.m
//  RGBTrainer
//
//  Created by 尾上 耕一 on 11/08/24.
//  Copyright 2011年 Koichi Onoue. All rights reserved.
//

#import "RGBTrainerViewController.h"

@implementation RGBTrainerViewController

@synthesize picker;
@synthesize colorView;
@synthesize questionView;
@synthesize pickerData;
@synthesize textField0;
@synthesize textField1;
@synthesize textField2;
@synthesize textField3;
@synthesize textField4;
@synthesize textField5;
@synthesize answerTextField0;
@synthesize answerTextField1;
@synthesize answerTextField2;

- (void)didReceiveMemoryWarning
{
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc that aren't in use.
}

#pragma mark - View lifecycle

// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad
{
    [super viewDidLoad];
	
	NSMutableArray* array = [[NSMutableArray alloc] init];
	for (int i = 0; i < 256; ++i) {
		NSString* string = [NSString stringWithFormat:@"%d", i];
		[array addObject:string];
	}
	self.pickerData = array;
	[array release];
	
	[textField0 setEnabled:NO];
	[textField1 setEnabled:NO];
	[textField2 setEnabled:NO];
	[textField3 setEnabled:NO];
	[textField4 setEnabled:NO];
	[textField5 setEnabled:NO];
	[answerTextField0 setEnabled:NO];
	[answerTextField1 setEnabled:NO];
	[answerTextField2 setEnabled:NO];

	srand(time(NULL));
	[self nextButtonPressed];
}

- (void)viewDidUnload
{
	self.picker = nil;
	self.colorView = nil;
	self.questionView = nil;
	self.pickerData = nil;
	self.textField0 = nil;
	self.textField1 = nil;
	self.textField2 = nil;
	self.textField3 = nil;
	self.textField4 = nil;
	self.textField5 = nil;
	self.answerTextField0 = nil;
	self.answerTextField1 = nil;
	self.answerTextField2 = nil;
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}

- (void)dealloc {
	[picker release];
	[colorView release];
	[questionView release];
	[pickerData release];
	[textField0 release];
	[textField1 release];
	[textField2 release];
	[textField3 release];
	[textField4 release];
	[textField5 release];
	[answerTextField0 release];
	[answerTextField1 release];
	[answerTextField2 release];
	[super dealloc];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView{
	return 6;
//	return 3;
}

- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component{
	
	return 16;
//	return 256;
}

- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component{
	
	return [NSString stringWithFormat:@"%x", row];
//	return [NSString stringWithFormat:@"%d", row];
}

- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component{
	
	NSInteger row0 = [picker selectedRowInComponent:0];
	NSInteger row1 = [picker selectedRowInComponent:1];
	NSInteger row2 = [picker selectedRowInComponent:2];
	NSInteger row3 = [picker selectedRowInComponent:3];
	NSInteger row4 = [picker selectedRowInComponent:4];
	NSInteger row5 = [picker selectedRowInComponent:5];
	
	int red = (row0 << 4 | row1);
	int green = (row2 << 4 | row3);
	int blue = (row4 << 4 | row5);
	float fRed = red / 255.0f;
	float fGreen = green / 255.0f;
	float fBlue = blue / 255.0f;
	
//	float red = row0 / 255.0f;
//	float green = row1 / 255.0f;
//	float blue = row2 / 255.0f;
	
//	[colorView setBackgroundColor:[UIColor colorWithRed:red green:green	blue:blue alpha:1.0f]];	
	[textField0 setText:[NSString stringWithFormat:@"%d", red]];
	[textField1 setText:[NSString stringWithFormat:@"%d", green]];
	[textField2 setText:[NSString stringWithFormat:@"%d", blue]];
	[textField3 setText:[NSString stringWithFormat:@"%f", fRed]];
	[textField4 setText:[NSString stringWithFormat:@"%f", fGreen]];
	[textField5 setText:[NSString stringWithFormat:@"%f", fBlue]];
}

- (IBAction)nextButtonPressed {
	
	_red = (float)rand() / RAND_MAX;
	_green = (float)rand() / RAND_MAX;
	_blue = (float)rand() / RAND_MAX;
	[questionView setBackgroundColor:[UIColor colorWithRed:_red green:_green blue:_blue alpha:1.0f]];	
	[colorView setBackgroundColor:[UIColor colorWithRed:_red green:_green blue:_blue alpha:1.0f]];		
}

- (IBAction)answerButtonPressed {
	
	NSInteger row0 = [picker selectedRowInComponent:0];
	NSInteger row1 = [picker selectedRowInComponent:1];
	NSInteger row2 = [picker selectedRowInComponent:2];
	NSInteger row3 = [picker selectedRowInComponent:3];
	NSInteger row4 = [picker selectedRowInComponent:4];
	NSInteger row5 = [picker selectedRowInComponent:5];
	
	int red = (row0 << 4 | row1);
	int green = (row2 << 4 | row3);
	int blue = (row4 << 4 | row5);
	float fRed = red / 255.0f;
	float fGreen = green / 255.0f;
	float fBlue = blue / 255.0f;
	[colorView setBackgroundColor:[UIColor colorWithRed:fRed green:fGreen	blue:fBlue alpha:1.0f]];	
	
	int answerRed = (int)(_red * 255.0f);
	int answerGreen = (int)(_green * 255.0f);
	int answerBlue = (int)(_blue * 255.0f);
	[answerTextField0 setText:[NSString stringWithFormat:@"%02x (%03d)", answerRed, answerRed]];
	[answerTextField1 setText:[NSString stringWithFormat:@"%02x (%03d)", answerGreen, answerGreen]];
	[answerTextField2 setText:[NSString stringWithFormat:@"%02x (%03d)", answerBlue, answerBlue]];
	
	int dRed = red - answerRed;
	int dGreen = green - answerGreen;
	int dBlue = blue - answerBlue;
	NSString* title = [[NSString alloc] initWithFormat:@"Difference RGB(%d, %d, %d)", dRed, dGreen, dBlue];
	
//	float dRed = 100.0f - fabs(red - answerRed) / 255.0f * 100.0f;
//	float dGreen = 100.0f - fabs(green - answerGreen) / 255.0f * 100.0f;
//	float dBlue = 100.0f - fabs(blue - answerBlue) / 255.0f * 100.0f;
//	NSString* title = [[NSString alloc] initWithFormat:@"Red %f%%, Green %f%%, Blue %f%%", dRed, dGreen, dBlue];
	UIAlertView* alert = [[UIAlertView alloc] initWithTitle:title message:@"Nice!" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil];
	[alert show];
	[alert release];
	[title release];	
	
//	NSString* selected = [pickerData objectAtIndex:row];
//	NSString* selected1 = [pickerData objectAtIndex:row1];
//	NSString* selected2 = [pickerData objectAtIndex:row2];
//	
//	NSString* title = [[NSString alloc] initWithFormat:@"You selected %@, %@, %@!", selected, selected1, selected2];
//	UIAlertView* alert = [[UIAlertView alloc] initWithTitle:title message:@"Thank you for choosing." delegate:nil cancelButtonTitle:@"You're Welcome" otherButtonTitles:nil];
//	[alert show];
//	[alert release];
//	[title release];	
}

@end

//
//  BMViewController.m
//  BM1
//
//  Created by 耕一 尾上 on 6/3/12.
//  Copyright (c) 2012 Koichi Onoue. All rights reserved.
//

#import "BMViewController.h"

@interface BMViewController ()

@end

@implementation BMViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.

    NSURL *url = [NSURL fileURLWithPath:@"/dev/null"];
    NSDictionary *settings = [NSDictionary dictionaryWithObjectsAndKeys:
        [NSNumber numberWithFloat: 44100.0], AVSampleRateKey,
        [NSNumber numberWithInt:kAudioFormatAppleLossless], AVFormatIDKey,
        [NSNumber numberWithInt:1], AVNumberOfChannelsKey,
        [NSNumber numberWithInt:AVAudioQualityMax], AVEncoderAudioQualityKey,
        nil];
    NSError *error;
    recorder = [[AVAudioRecorder alloc] initWithURL:url settings:settings error:&error];
    if (recorder) {
        [recorder prepareToRecord];
        recorder.meteringEnabled = YES;
        [recorder record];
        levelTimer = [NSTimer scheduledTimerWithTimeInterval:0.03 target:self selector:@selector(levelTimerCallback:) userInfo:nil repeats:YES];

    } else {
        NSLog([error description]);
    }
    lowPassresults = 0;
}

- (void)levelTimerCallback:(NSTimer *)timer {
    [recorder updateMeters];

    const double ALPHA = 0.05;
    double peakPowerForChannel = pow(10, (0.05 * [recorder peakPowerForChannel:0]));
    lowPassresults = ALPHA * peakPowerForChannel + (1.0 - ALPHA) * lowPassresults;
    NSLog(@"Average input: %f Peak input: %f Low pass results: %f", [recorder averagePowerForChannel:0], [recorder peakPowerForChannel:0], lowPassresults);
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

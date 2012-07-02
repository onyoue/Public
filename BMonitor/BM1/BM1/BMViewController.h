//
//  BMViewController.h
//  BM1
//
//  Created by 耕一 尾上 on 6/3/12.
//  Copyright (c) 2012 Koichi Onoue. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>
#import <CoreAudio/CoreAudioTypes.h>


@interface BMViewController : UIViewController {
    AVAudioRecorder *recorder;
    NSTimer *levelTimer;
    double lowPassresults;
}

- (void)levelTimerCallback:(NSTimer *)timer;
@end

//
//  KMViewController.h
//  Koma0
//
//  Created by 尾上 耕一 on 12/04/04.
//  Copyright (c) 2012年 Koichi Onoue. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface KMViewController : UIViewController <UIImagePickerControllerDelegate, UINavigationControllerDelegate>
@property (weak, nonatomic) IBOutlet UIImageView *imageView;
//@property (strong, nonatomic) NSMutableArray *images;

@end

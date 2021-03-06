/*------------------------------------------------------------------------
* (The MIT License)
* 
* Copyright (c) 2008-2011 Rhomobile, Inc.
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
* 
* http://rhomobile.com
*------------------------------------------------------------------------*/

#import <UIKit/UIKit.h>
#import "RhoMainView.h"
#import "RhoViewController.h"


@interface RhoUISplitView : UIView {
	int leftWidth;
}
@property (nonatomic,assign) int leftWidth;

- (void)layoutSubviews;

@end


@interface SplittedMainView : RhoViewController<RhoMainView> {
	NSArray* viewControllers;
    id<RhoMainView> mCreationTimeMainView;
}

@property (nonatomic,retain) NSArray *viewControllers;

- (id)initWithMainView:(id<RhoMainView>)v parent:(UIWindow*)p bar_info:(NSDictionary*)bar_info;

//- (UIView*)view;

- (void)loadHTMLString:(NSString*)data;

- (void)back:(int)index;
- (void)forward:(int)index;
- (void)navigate:(NSString*)url tab:(int)index;
- (void)navigateRedirect:(NSString*)url tab:(int)index;
- (void)reload:(int)index;

- (void)executeJs:(NSString*)js tab:(int)index;

- (NSString*)currentLocation:(int)index;

- (void)switchTab:(int)index;
- (int)activeTab;

- (void)addNavBar:(NSString*)title left:(NSArray*)left right:(NSArray*)right;
- (void)removeNavBar;

- (id<RhoWebView,NSObject>)getRhoWebView:(int)tab_index;
- (id<RhoWebView,NSObject>)detachRhoWebView;

- (id<RhoMainView>)getCreationTimeMainView;




@end



#include <ruby.h>
#include "ruby/ext/rho/rhoruby.h"


#ifdef __cplusplus
extern "C" {
#endif

// declare Ruby registration method generated by SWIG in audiocapture_wrap.c
extern void Init_AudioCaptureNative(void);

// this method executed once on start of program
void Init_AudioCapture_extension(void) {
	// execute initialization of Ruby registration (generated by SWIG)
	Init_AudioCaptureNative();

	// You can add some code to this place:
    rb_require("audiocapture");

}

#ifdef __cplusplus
} //extern "C"
#endif
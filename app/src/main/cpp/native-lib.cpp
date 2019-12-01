#include <jni.h>
#include <string>
#include <opencv2/core/core.hpp>
#include <opencv2/imgproc/imgproc.hpp>

using namespace cv;


extern "C" {
JNIEXPORT void JNICALL
Java_com_example_opencval_MainActivity_ConvertRGBtoGray(JNIEnv *env, jobject thiz,
                                                        jlong mat_addr_lnput,
                                                        jlong mat_addr_result) {
    // TODO
    Mat &matInput = *(Mat *) mat_addr_lnput;
    Mat &matResult = *(Mat *) mat_addr_result;

    cvtColor(matInput, matResult, COLOR_RGBA2GRAY);
}
}
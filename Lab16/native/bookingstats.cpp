#include "ua_khpi_oop_lab16_NativeBookingStatistics.h"
#include <stdexcept>

JNIEXPORT jdouble JNICALL Java_ua_khpi_oop_lab16_NativeBookingStatistics_average
  (JNIEnv* env, jobject, jintArray values) {
    if (values == nullptr) {
        jclass exceptionClass = env->FindClass("java/lang/IllegalArgumentException");
        env->ThrowNew(exceptionClass, "Input array must not be null");
        return 0.0;
    }

    jsize length = env->GetArrayLength(values);
    if (length == 0) {
        jclass exceptionClass = env->FindClass("java/lang/IllegalArgumentException");
        env->ThrowNew(exceptionClass, "Input array must not be empty");
        return 0.0;
    }

    jint* elements = env->GetIntArrayElements(values, nullptr);
    if (elements == nullptr) {
        return 0.0;
    }

    long long sum = 0;
    for (jsize i = 0; i < length; ++i) {
        sum += elements[i];
    }

    env->ReleaseIntArrayElements(values, elements, JNI_ABORT);
    return static_cast<jdouble>(sum) / static_cast<jdouble>(length);
}

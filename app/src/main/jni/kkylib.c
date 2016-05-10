//
// Created by Gavin on 2016/5/8.
//
#include "com_kuaikuaiyu_assistant_utils_JniUtil.h"
#include <android/log.h>
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG , "KKY", __VA_ARGS__)

jstring CharTojstring(JNIEnv *env, char *str) {
    jsize len = strlen(str);

    jclass clsstring = (*env)->FindClass(env, "java/lang/String");
    jstring strencode = (*env)->NewStringUTF(env, "GB2312");

    jmethodID mid = (*env)->GetMethodID(env, clsstring, "<init>", "([BLjava/lang/String;)V");
    jbyteArray barr = (*env)->NewByteArray(env, len);

    (*env)->SetByteArrayRegion(env, barr, 0, len, (jbyte *) str);
    return (jstring)(*env)->NewObject(env, clsstring, mid, barr, strencode);
}

JNIEXPORT jstring

JNICALL Java_com_kuaikuaiyu_assistant_utils_JniUtil_sign
        (JNIEnv *env, jobject obj, jstring str) {
    char *cstr = (*env)->GetStringUTFChars(env, str, 0);
    LOGD("sig = %s", cstr);
    char *signature = sign(cstr);
    LOGD("sig = %s", signature);
    return CharTojstring(env, signature);
}
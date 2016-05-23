//
// Created by Gavin on 2016/5/8.
//
#include "com_kuaikuaiyu_assistant_utils_JniUtil.h"
#include <android/log.h>

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG , "KKY", __VA_ARGS__)

char *Jstring2CStr(JNIEnv *env, jstring jstr) {
    char *rtn = NULL;
    jclass clsstring = (*env)->FindClass(env, "java/lang/String");
    jstring strencode = (*env)->NewStringUTF(env, "UTF-8");
    jmethodID mid = (*env)->GetMethodID(env, clsstring, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr = (jbyteArray)(*env)->CallObjectMethod(env, jstr, mid,
                                                           strencode); // String .getByte("GB2312");
    jsize alen = (*env)->GetArrayLength(env, barr);
    jbyte *ba = (*env)->GetByteArrayElements(env, barr, JNI_FALSE);
    if (alen > 0) {
        rtn = (char *) malloc(alen + 1);         //"\0"
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }
    (*env)->ReleaseByteArrayElements(env, barr, ba, 0);  //
    return rtn;
}

JNIEXPORT jstring JNICALL Java_com_kuaikuaiyu_assistant_utils_JniUtil_sign
        (JNIEnv *env, jobject obj, jstring str) {
//    char *cstr = Jstring2CStr(env, str);
    char* cstr = (*env)->GetStringUTFChars(env, str, 0);
    char *signature[64];
    signs(cstr, signature);
    return (*env)->NewStringUTF(env, signature);
}
//
// Created by Gavin on 2016/5/8.
//

#include "com_kuaikuaiyu_assistant_utils_JniUtil.h"

JNIEXPORT jstring JNICALL Java_com_kuaikuaiyu_assistant_utils_JniUtil_getSignKey
  (JNIEnv *env, jobject obj)
{
    return (*env)->NewStringUTF(env,"0f654197bba48eac7a36d32dae278a7ab4e1d29c80ad80d5617c5a555c0b8381");
}
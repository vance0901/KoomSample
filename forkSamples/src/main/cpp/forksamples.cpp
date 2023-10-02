#include <jni.h>
#include <string>
#include <unistd.h>
#include <sys/prctl.h>
#include <dlfcn.h>

extern "C"
JNIEXPORT jint JNICALL
Java_com_enjoy_forksamples_NativeLib_fork(JNIEnv *env, jobject thiz) {
    int pid = fork();
    if (pid == 0) {
        // 子进程...
        //....
        prctl(PR_SET_NAME, "forked-child-process");
    }else if(pid >0){
        // 父进程
        //....
    }
    return pid;
}

void a() {
    // 打开libart.so
    void *handler = dlopen("/system/lib/libart.so", RTLD_NOW);
    // 定义函数指针suspend_vm_fnc_ 返回类型 (*指针名)(参数类型)
    void (*suspend_vm_fnc_)();
    // 从libart.so 中找到符号地址
    suspend_vm_fnc_ = (void (*)()) dlsym(handler, "_ZN3art3Dbg9SuspendVMEv");
    // 执行函数
    suspend_vm_fnc_();

    dlclose(handler);
}
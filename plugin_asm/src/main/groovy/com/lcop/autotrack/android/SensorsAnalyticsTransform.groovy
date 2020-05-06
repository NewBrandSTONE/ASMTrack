package com.lcop.autotrack.android

import com.android.build.api.transform.Context
import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformOutputProvider
import com.android.utils.FileUtils
import org.gradle.api.Project
import org.gradle.internal.impldep.org.apache.commons.codec.digest.DigestUtils
import org.gradle.internal.impldep.org.apache.ivy.util.FileUtil


public class SensorsAnalyticsTransform extends Transform {

    private static Project project

    public SensorsAnalyticsTransform(Project project) {
        this.project = project
    }

    @Override
    String getName() {
        return "SensorsAnalyticsAutoTrack"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    void transform(Context context
                   , Collection<TransformInput> inputs
                   , Collection<TransformInput> referencedInputs
                   , TransformOutputProvider outputProvider
                   , boolean isIncremental) throws IOException, TransformException, InterruptedException {
        printCopyRight()
        // 需要分开遍历.class和.jar文件
        inputs.each { TransformInput input ->
            input.directoryInputs.each { DirectoryInput directoryInput ->
                // 获取 output 目录
                def dest = outputProvider.getContentLocation(directoryInput.name
                        , directoryInput.contentTypes, directoryInput.scopes
                        , Format.DIRECTORY)
                // 将 input 目录复制到 output 指定目录
                FileUtils.copyDirectory(directoryInput.file, dest)
            }
            // 遍历 jar 文件
            input.jarInputs.each { JarInput jarInput ->
                // 重命名输出文件
                def jarName = jarInput.name
                def md5Name = DigestUtils.md5(jarInput.file.getAbsolutePath())
                if (jarName.endsWith(".jar")) {
                    jarName = jarName.substring(0, jarName.length() - 4)
                }
                File copyJarFile = jarInput.file
                // 生成输出路径
                def dest = outputProvider.getContentLocation(jarName + md5Name
                        , jarInput.contentTypes
                        , jarInput.scopes
                        , Format.JAR)
                FileUtil.copy(copyJarFile, dest)
            }
        }
    }

    @Override
    boolean isIncremental() {
        return false
    }

    /**
     * 打印提示信息
     */
    static void printCopyRight() {
        println()
        println("####################################################################")
        println("########                                                    ########")
        println("########                                                    ########")
        println("########         欢迎使用 SensorsAnalytics® 编译插件        ########")
        println("########          使用过程中碰到任何问题请联系我们          ########")
        println("########                                                    ########")
        println("########                                                    ########")
        println("####################################################################")
        println()
    }
}
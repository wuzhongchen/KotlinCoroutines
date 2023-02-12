package com.dongnaoedu.flowpractice.download

import java.io.File

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
sealed class DownloadStatus {
    object None : DownloadStatus()
    data class Progress(val value: Int) : DownloadStatus()
    data class Error(val throwable: Throwable) : DownloadStatus()
    data class Done(val file: File) : DownloadStatus()

}

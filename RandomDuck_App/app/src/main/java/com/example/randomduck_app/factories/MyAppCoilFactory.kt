package com.example.randomduck_app.factories

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

class MyAppCoilFactory : Application(), ImageLoaderFactory {
    override fun newImageLoader() : ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(ImageDecoderDecoder.Factory())
                add(GifDecoder.Factory())
            }
            .allowHardware(false)
            .build()
    }
}
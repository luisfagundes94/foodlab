package com.luisfagundes.framework.decoder.di

import com.luisfagundes.framework.decoder.StringDecoder
import com.luisfagundes.framework.decoder.UriDecoder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StringDecoderModule {
    @Binds
    abstract fun bindStringDecoder(uriDecoder: UriDecoder): StringDecoder
}
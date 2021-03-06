package com.hanmajid.android.pokemonapp.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

/**
 * The app's Koin application-level Dependency Injection module.
 */
@Module(includes = [DatabaseSpModule::class])
@ComponentScan("com.hanmajid.android.pokemonapp")
class AppModule
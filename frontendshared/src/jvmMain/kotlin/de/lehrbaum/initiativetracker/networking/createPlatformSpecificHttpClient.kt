package de.lehrbaum.initiativetracker.networking

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.OkHttpClient
import kotlin.time.toJavaDuration

actual fun createPlatformSpecificHttpClient(initializationBlock: HttpClientConfig<*>.() -> Unit): HttpClient =
	HttpClient(OkHttp) {
		engine {
			preconfigured = OkHttpClient.Builder()
				.pingInterval(WEBSOCKET_PING_INTERVAL.toJavaDuration())
				.build()
		}
		initializationBlock()
	}

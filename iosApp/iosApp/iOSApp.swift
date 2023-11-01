import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        KoinIosModuleKt.doInitKoinIos(
            doOnStartup: { NSLog("Hello from iOS") }
        )
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}

import Foundation
import UIKit
import MSAL
import ComposeApp

class AppDelegate: NSObject, UIApplicationDelegate {
    var window: UIWindow?

    override init() {
        super.init()
        MSALGlobalConfig.brokerAvailability = .none
        MSALGlobalConfig.loggerConfig.setLogCallback { logLevel, message, containsPII in
            if containsPII {
                print("MSAL Log (PII): \(message ?? "No message")")
            } else {
                print("MSAL Log: \(message ?? "No message")")
            }
        }
        MSALGlobalConfig.loggerConfig.logLevel = .verbose
    }

    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey: Any] = [:]) -> Bool {
        print("🔥 CALLBACK URL:", url.absoluteString)
        return MSALPublicClientApplication.handleMSALResponse(url, sourceApplication: options[.sourceApplication] as? String)
    }

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        print("AppDelegate: didFinishLaunchingWithOptions")
        return true
    }
}

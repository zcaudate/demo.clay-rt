(defproject demo/clay-rt "0.1.0-SNAPSHOT"
  :description "demo for clay and std.lang"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.scicloj/clay "2-beta28"]
                 [xyz.zcaudate/std.lang            "4.0.4"]
                 [xyz.zcaudate/std.make            "4.0.4"]

                 ;; runtimes
                 [xyz.zcaudate/rt.basic            "4.0.4"]
                 [xyz.zcaudate/rt.shell            "4.0.4"]
                 [xyz.zcaudate/rt.redis            "4.0.4"]
                 [xyz.zcaudate/rt.postgres         "4.0.4"]

                 ;; js
                 [xyz.zcaudate/js.core             "4.0.4"]
                 [xyz.zcaudate/js.blessed          "4.0.4"]
                 [xyz.zcaudate/js.react            "4.0.4"]
                 
                 ;; kmi
                 [xyz.zcaudate/kmi.redis           "4.0.4"]

                 ;; xtalk
                 [xyz.zcaudate/xtalk.lang          "4.0.4"]
                 [xyz.zcaudate/xtalk.system        "4.0.4"]]
  :profiles {:dev {:dependencies [[xyz.zcaudate/code.test           "4.0.4"]
                                  [xyz.zcaudate/code.manage         "4.0.4"]
                                  [xyz.zcaudate/code.java           "4.0.4"]
                                  [xyz.zcaudate/code.maven          "4.0.4"]
                                  [xyz.zcaudate/code.doc            "4.0.4"]
                                  [xyz.zcaudate/code.dev            "4.0.4"]]
                   :plugins [[lein-ancient "0.6.15"]
                             [lein-exec "0.3.7"]
                             [cider/cider-nrepl "0.45.0"]]}
             :repl {:injections []}})

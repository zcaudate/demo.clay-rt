(ns demo.tui-000-counter.build
  (:use [code.test :exclude [-main]])
  (:require [std.lang :as l]
            [std.lib :as h]
            [std.make :as make :refer [def.make]]
            [js.webpack :as webpack]
            [demo.tui-000-counter.main :as main]))

(def.make PROJECT
  {:tag       "tui-000-counter"
   :build     ".build/tui-000-counter"
   :github   {:repo "zcaudate/demo.tui-000-counter"}
   :orgfile  "Main.org"
   :sections {:setup  [webpack/+node-basic+
                       webpack/+node-makefile+
                       webpack/+node-gitignore+
                       {:type :package.json
                        :main {"name" "tui-000-counter",
                               "main" "src/main.js",
                               "scripts"
                               {"dev" "npx webpack --watch",
                                "package" "npx webpack --env prod"},
                               "dependencies"
                               {"blessed" "0.1.81",
                                "raf" "3.4.1",
                                "react" "17.0.1",
                                "react-blessed" "0.7.0"},
                               "devDependencies"
                               {"@sucrase/webpack-loader" "^2.0.0",
                                "cache-loader" "^4.1.0",
                                "run-node-webpack-plugin" "^1.3.0",
                                "source-map-support" "^0.5.19",
                                "sucrase" "^3.17.0",
                                "webpack" "^5.12.1",
                                "webpack-cli" "^4.3.1",
                                "webpack-node-externals" "^2.5.2"}}}]}
   :default  [{:type   :module.single
               :lang   :js
               :main   'demo.tui-000-counter.main
               :file   "src/main.js"
               :emit   {:code   {:label true}
                        :export {:suppress true}
                        :link   {:suppress true}}}]})

(def +init+
  (do (make/triggers-set PROJECT '#{demo.tui-000-counter.main})))

(defn -main
  []
  (make/build-all PROJECT)
  (make/gh:dwim-init PROJECT))

^{:eval false
  ;;
  ;; BUILD SETUP
  ;;
  }
(fact "Code FOR PROJECT SETUP" 

  (make/build-all PROJECT))

^{:eval false
  ;;
  ;; BUILD SETUP
  ;;
  }
(fact "Code FOR PROJECT SETUP" 

  (make/run-internal PROJECT :init))

^{:eval false
  ;;
  ;; BUILD SETUP
  ;;
  }
(fact "Code FOR PROJECT SETUP" 

  (make/run-internal PROJECT :dev))

^{:eval false
  ;;
  ;; GH INITAL SETUP
  ;;
  :ui/action [:GITHUB :SETUP]}
(fact "initial setup of repo from github"

  (make/gh:dwim-init PROJECT))

^{:eval false
  ;;
  ;; GH PUSH NEWEST
  ;;
  :ui/action [:GITHUB :PUSH]}
(fact "pushes changes to github"

  (make/gh:dwim-push PROJECT))


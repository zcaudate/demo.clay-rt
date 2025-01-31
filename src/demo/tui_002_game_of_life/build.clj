(ns demo.tui-002-game-of-life.build
  (:use [code.test :exclude [-main]])
  (:require [std.lang :as  l]
            [std.lib :as h]
            [std.make :as make :refer [def.make]]
            [js.webpack :as webpack]
            [demo.tui-002-game-of-life.main :as main]))

(def.make PROJECT
  {:tag       "tui-002-game-of-life"
   :build     ".build/tui-002-game-of-life"
   :github   {:repo "zcaudate/demo.tui-002-game-of-life"
              :description "Conway's Game of Life for React Blessed"}
   :orgfile  "Main.org"
   :sections {:setup  [webpack/+node-basic+
                       webpack/+node-makefile+
                       webpack/+node-gitignore+
                       {:type :package.json
                        :main {"name" "tui-002-game-of-life",
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
   :default  [{:type   :module.graph
               :lang   :js
               :target "src"
               :main   'demo.tui-002-game-of-life.main
               :emit   {:code   {:label true}}}]})

(def +init+
  (do (make/triggers-set PROJECT '#{demo.tui-002-game-of-life.main})))

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
  ;; RUN DEV
  ;;
  }
(fact "Code FOR PROJECT SETUP" 

  (make/run PROJECT :run))

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


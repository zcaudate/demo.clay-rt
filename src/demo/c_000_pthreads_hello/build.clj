(ns demo.c-000-pthreads-hello.build
  (:use [code.test :exclude [-main]])
  (:require [std.lang :as l]
            [std.lib :as h]
            [std.make :as make :refer [def.make]]
            [demo.c-000-pthreads-hello.main :as main]))

(def +makefile+
  [{:CC :gcc
    :CFLAGS ["-I."]}
   ["out/%.o" {:- ["src/%.c"]}
    ["@mkdir -p out"]
    ["$(CC) -c -o $@ $< $(CFLAGS)"]]
   [:package {:- ["out/pthreads_hello.o"]}
    ["@mkdir -p bin"]
    ["$(CC) -o bin/pthreads_hello out/pthreads_hello.o" "-lpthread"]]
   [:run {:- [:package]}
    ["./bin/pthreads_hello"]]])

(def.make PROJECT
  {:github   {:repo "zcaudate/demo.c-000-pthreads-hello"
              :description "Simple Posix Threads Example"}
   :orgfile  "Main.org"
   :sections {:setup  [{:type :gitignore
                        :main ["bin" "out"]}
                       {:type :makefile
                        :main +makefile+}]}
   :default  [{:type :module.single
               :lang :c
               :main 'demo.c-000-pthreads-hello.main
               :file "pthreads_hello.c"
               :target "src"}]})

(def +init+
  (do (make/triggers-set PROJECT '#{demo.c-000-pthreads-hello.main})))

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

  (make/build-all PROJECT)
  (make/gh:dwim-push PROJECT))

^{:eval false
  ;;
  ;; BUILD SETUP
  ;;
  }
(fact "Code FOR PROJECT SETUP" 

  (make/run-internal PROJECT :package)
  (make/run-internal PROJECT :run))

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

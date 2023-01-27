(ns run
  "Setup stuff for the action."
  (:require
    [babashka.process :refer [sh]]
    [clojure.string :as str]))

(def GH-OUTPUT (System/getenv "GITHUB_OUTPUT"))

(def clj-path "/usr/local/bin/clojure")
(def clj-version (-> (sh "clojure --version") :out (str/split #" ") last))

(def babashka-path "/usr/local/bin/bb")
(def babashka-version (-> (sh "bb --version") :out (str/split #" ") last))

(def java-version (-> (sh "java -version") :out str/split-lines first))

(spit GH-OUTPUT (format "java_version=%s\n" java-version) :append true)
(spit GH-OUTPUT (format "clojure_path=%s\n" clj-path) :append true)
(spit GH-OUTPUT (format "clojure_version=%s\n" clojure-version) :append true)
(spit GH-OUTPUT (format "babashka_path=%s\n" babashka-path) :append true)
(spit GH-OUTPUT (format "babashka_version=%s\n" babashka-version) :append true)

; (sh (format "echo \"java_version=%s\" >> $GITHUB_OUTPUT" java-version))
; (sh (format "echo \"clojure_path=%s\" >> $GITHUB_OUTPUT" clj-path))
; (sh (format "echo \"clojure_version=%s\" >> $GITHUB_OUTPUT" clj-version))
; (sh (format "echo \"babashka_path=%s\" >> $GITHUB_OUTPUT" babashka-path))
; (sh (format "echo \"babashka_version=%s\" >> $GITHUB_OUTPUT" babashka-version))
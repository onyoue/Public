(use '[clojure.contrib.duck-streams :only (reader writer write-lines)])

;(doseq [l (line-seq (reader "/Users/onyoue/MyProject/GLSLEditor/src/glsledit/jcclexer/builtin_functions.txt"))]
;  (println (format "\"%s\" \"builtin-function\"," l )))

(write-lines (writer "/Users/onyoue/MyProject/GLSLEditor/src/glsledit/jcclexer/tmp.clj")
  (map #(format "\"%s\" \"builtin_function\"," %)
    (line-seq (reader "/Users/onyoue/MyProject/GLSLEditor/src/glsledit/jcclexer/builtin_functions.txt"))))

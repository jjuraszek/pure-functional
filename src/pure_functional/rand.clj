(ns pure-functional.rand)

(defn linear-congruential
  "return function which is generating random number vector on provided seed"
  [seed]
  (fn [howManyRandNum]
    (map #(* -1 %) (range howManyRandNum))))
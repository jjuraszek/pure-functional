(ns pure-functional.find)

(defn triple-to-zero
  "find number of sets of triples indexes summing to zero"
  [v]
  (println (sort v))
  1)

(defn count-summing-pairs
  "find in vector count of pairs of two numbers summing to third number, input vector do not have duplicates"
  ([sortedVector  desireSum] (count-summing-pairs sortedVector desireSum 0))
  ([sortedVector  desireSum currentCount] 
    (let [sum (+ (first sortedVector) (first (take-last 1 sortedVector)))
          head (subvec sortedVector 0 (dec (count sortedVector)))
          tail (subvec sortedVector 1)
          end (= 1 (count sortedVector))]
      (if end 
        currentCount
        (if (= sum desireSum)
          (count-summing-pairs (subvec sortedVector 1) desireSum (inc currentCount))
          (if (< sum desireSum)
            (count-summing-pairs  tail desireSum currentCount)
            (count-summing-pairs  head desireSum currentCount)))))))
(ns pure-functional.find)

(declare count-summing-pairs)

(defn triple-to-zero
  "find number of sets of triples indexes summing to zero"
  [v]
  (let [sortedVector             (vec (sort v))
        sortedVectorWithoutLast2 (subvec sortedVector 0 (- (count v) 2))
        countSum                 (fn [idx item] (count-summing-pairs (subvec sortedVector (inc idx)) (* -1 item)))
        vectorOfCounts           (keep-indexed countSum sortedVectorWithoutLast2)]
    (reduce + vectorOfCounts)))

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
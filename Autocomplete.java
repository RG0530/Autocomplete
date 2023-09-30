import java.util.Arrays;


/**
 * Autocomplete
 *    Defines a class that holds a dictionary of terms to be returned--
 *    given the proper prefix--to form a proper autocomplete prompt.
 */
public class Autocomplete {

	private Term[] terms;

	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
	public Autocomplete(Term[] _terms) {
      if (_terms == null) throw new NullPointerException();
      terms = _terms;
      Arrays.sort(terms, Term.byPrefixOrder(Integer.MAX_VALUE));
    }

	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
	 */
	public Term[] allMatches(String prefix) {
	   if (prefix == null) throw new NullPointerException();
      
      Term prefixKey = new Term(prefix, 0);
      int lowKey = BinarySearch.<Term>firstIndexOf(terms, prefixKey, Term.byPrefixOrder(prefix.length()));
      int highKey = BinarySearch.<Term>lastIndexOf(terms, prefixKey, Term.byPrefixOrder(prefix.length()));
      Term[] autoCompleteFeed = new Term[highKey - lowKey + 1]; 
      for (int i = lowKey; i <= highKey; i++) {
         autoCompleteFeed[i-lowKey] = terms[i];
      }
      
      Arrays.sort(autoCompleteFeed, Term.byDescendingWeightOrder());
      return autoCompleteFeed;
    }
    
    public Term[] getTerms() {
      return terms;
    }

}


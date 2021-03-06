package kikaha.core.cdi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import kikaha.core.cdi.helpers.filter.ChainedCondition;
import kikaha.core.cdi.helpers.filter.Condition;
import lombok.RequiredArgsConstructor;
import lombok.val;

import org.junit.Test;

public class ChainedConditionTest {

	@Test
	public void ensureThatNumberIsBetweenARange() {
		val between = new ChainedCondition<Integer>();
		between.add( new LowerThan( 13 ) );
		between.add( new GreaterThan( 5 ) );
		assertTrue( between.check( 8 ) );
		assertTrue( between.check( 12 ) );
		assertFalse( between.check( 18 ) );
		assertFalse( between.check( 5 ) );
	}

}

@RequiredArgsConstructor
class GreaterThan implements Condition<Integer> {

	final Integer number;

	@Override
	public boolean check( Integer target ) {
		return target > number;
	}
}

@RequiredArgsConstructor
class LowerThan implements Condition<Integer> {

	final Integer number;

	@Override
	public boolean check( Integer target ) {
		return target < number;
	}
}
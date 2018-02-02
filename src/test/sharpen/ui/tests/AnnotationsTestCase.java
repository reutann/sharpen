package sharpen.ui.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.junit.Test;
import sharpen.core.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class AnnotationsTestCase extends AbstractConversionTestCase {

	@Test
	public void testFoo() {
		assertEquals("Com.Microsoft.Intune.Mam.IClient", toInterface("com.microsoft.intune.mam.client"));
		assertEquals("Com.Microsoft.Intune.Mam.Client", toNamespace("com.microsoft.intune.mam.client"));
		assertEquals("Com.Microsoft.Intune.Mam.Client", toNamespace("com.microsoft.intune.mam.Client"));
		assertEquals("Com.Microsoft.Intune.Mam.client", type("com.microsoft.intune.mam.client"));
	}

	private String toInterface(final String type) {
		return pascale(type, true, true);
	}

	private String toNamespace(final String type) {
		return pascale(type, true, false);
	}

	private String type(final String type) {
		return pascale(type, false, false);
	}

	private String pascale(final String str, boolean doLast, boolean addInterface) {
		List<String> names = new ArrayList<String>(Arrays.asList(str.split("\\.")));
		String last = names.remove(names.size() - 1);

		StringBuilder pascaled = new StringBuilder();
		for (String part : names) {
			pascaled.append(part.substring(0, 1).toUpperCase());
			pascaled.append(part.substring(1));
			pascaled.append(".");
		}

		if (addInterface) {
			pascaled.append("I");
		}

		if (doLast) {
			pascaled.append(last.substring(0, 1).toUpperCase());
			pascaled.append(last.substring(1));
		} else {
			pascaled.append(last);
		}


		return pascaled.toString();
	}

//
//	@Test
//	public void testSimpleAnnotation() throws IOException, CoreException {
//		runResourceTestCase("SimpleAnnotation");
//	}
//
//	@Test
//	public void testCompilerAnnotations() throws IOException, CoreException {
//		runResourceTestCase("CompilerAnnotations");
//	}
//
//	@Override
//	protected void runResourceTestCase(String resourceName) throws IOException, CoreException {
//		super.runResourceTestCase("annotations/" + resourceName);
//	}
//
//	@Override
//	protected Configuration getConfiguration() {
//	    final Configuration config = super.getConfiguration();
//	    config.enableNativeInterfaces();
//		return config;
//	}
}

package org.jcaching.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import org.jcaching.config.Cacheable;

public class JCachingTransformer implements ClassFileTransformer {
	private static final String WRAPPED_METHOD_PREFIX = "____";
	public byte[] transform(ClassLoader loader, String className, Class<?> redefiningClass, ProtectionDomain domain, byte[] bytes)
			throws IllegalClassFormatException {
		return transformClass(redefiningClass, bytes);
	}

	private byte[] transformClass(Class<?> classToTransform, byte[] b) {
		ClassPool pool = ClassPool.getDefault();
		CtClass cl = null;
		try {
			cl = pool.makeClass(new java.io.ByteArrayInputStream(b));
			CtMethod[] methods = cl.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].isEmpty() == false) {
					changeMethod(methods[i]);
				}
			}
			b = cl.toBytecode();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cl != null) {
				cl.detach();
			}
		}
		return b;
	}

	private void changeMethod(CtMethod method) throws NotFoundException, CannotCompileException, ClassNotFoundException {
		Cacheable c = (Cacheable) method.getAnnotation(Cacheable.class);
		if (c != null) {
			CtMethod oldMethod = new CtMethod(method, method.getDeclaringClass(), null);
			oldMethod.setName(WRAPPED_METHOD_PREFIX+method.getName());
			method.getDeclaringClass().addMethod(oldMethod);
						
			StringBuilder body= new StringBuilder();
			body.append("{ "+MethodWrapper.class.getCanonicalName()+" __proxiedMethod = new "+MethodWrapper.class.getCanonicalName()+"(this,\""+method.getName()+"\",$args,"+c.timeout()+");");
			body.append("if(__proxiedMethod.get()!=null) return (");
			body.append(oldMethod.getReturnType().getName());
			body.append(")__proxiedMethod.get();");
			body.append("return (");
			body.append(oldMethod.getReturnType().getName());
			body.append(")__proxiedMethod.set($proceed($$)); }");
			method.setBody(body.toString() ,"this", oldMethod.getName());
		}
	}

}

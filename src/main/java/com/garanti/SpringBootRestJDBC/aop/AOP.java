package com.garanti.SpringBootRestJDBC.aop;


//@Aspect
//@Component
public class AOP
{
//    // içinde Ogrenci kelimesi geçen metodları yakaladım
//    // @Pointcut(value = "execution(public * *(..))")
//    @Pointcut(value = "execution(* com.garanti.SpringBootRestJDBC.controller.OgretmenController.*get*(..))")
//    // OgretmenController içindeki bütün metodlar
//    // @Pointcut(value = "execution(* com.garanti.SpringBootRestJDBC.controller.OgretmenController.*(..))")
//    private void ogretmengetpointcut()
//    {
//        System.err.println("==> all methods for ogretmen get operations");
//    }

//    // yukarıdaki metodun adı eşleşmek zorunda
//    @Before(value = "ogretmengetpointcut()")
//    public void beforeogretmengetpointcut()
//    {
//        System.err.println("==> before ogretmen get operations");
//    }
//
//    @After(value = "ogretmengetpointcut()")
//    public void afterogretmengetpointcut()
//    {
//        System.err.println("==> after ogretmen get operations");
//    }

//    // hem öncesi hem sonrası, before ve after 'ı kapsıyor
//    @Around(value = "ogretmengetpointcut()")
//    // @Around(value = "execution(* org.springframework.jdbc..*(..))")
//    // @Around(value = "execution(* com.garanti.SpringBootRestJDBC..*(..))")
//    public Object aroundogrenciPointcut(ProceedingJoinPoint point) throws Throwable
//    {
//        // System.err.println("==> around ogretmen get operations");
//        long startTime = System.currentTimeMillis();
//        // yazmazsam metodç çalışmaz
//        Object result = point.proceed();
//        long endtime = System.currentTimeMillis();
//        System.err.println("Class Name: " + point.getSignature().getDeclaringTypeName() + ". Method Name: " + point.getSignature().getName() + ". Time taken for Execution is : " + (endtime - startTime) + "ms");
//        // System.err.println("==> around ogretmen get operations 2");
//        // return etmezsem devam etmez
//        return result;
//    }

    // all implementations of interface
    // within(JPARepositoryImpl+)

    // -------------------------------------------------- //

// @Before(value = "execution(* com.bilgeadam.springboothibernate.controller.RestfulServiceJPA.getOgrenciByName(..))")
// public void beforeOgretmenGetAll(JoinPoint point)
// {
//    System.out.println("==> before ögrenci getOgrenciByName işlemi");
//    System.out.println("==> " + point.getSignature().getName());
//    System.out.println("==> " + point.getSignature().toLongString());
//    System.out.println("==> " + Arrays.toString(point.getArgs()));
// }

    // -------------------------------------------------- //
// @Around(value = "within(com.garanti.SpringBootRestJDBC..*)")
// @Around(value = "within(org.springframework.jdbc.core.namedparam..*)")
// private Object allMethodsInsideControllerPackage(ProceedingJoinPoint point) throws Throwable
//    {
//        long startTime = System.currentTimeMillis();
//        Object result = point.proceed();
//        long endtime = System.currentTimeMillis();
//        System.err.println("Class Name: " + point.getSignature().getDeclaringTypeName() + ". Method Name: " + point.getSignature().getName() + ". Time taken for Execution is : " + (endtime - startTime) + "ms");
//        return result;
// }
}
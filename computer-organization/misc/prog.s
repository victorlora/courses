	.file	"prog.c"
	.text
	.p2align 4,,15
.globl main
	.type	main, @function
main:
	pushl	%ebp
	xorl	%eax, %eax
	movl	%esp, %ebp
	movl	$19, num
	popl	%ebp
	ret
	.size	main, .-main
	.comm	num,4,4
	.ident	"GCC: (GNU) 4.4.6 20110731 (Red Hat 4.4.6-3)"
	.section	.note.GNU-stack,"",@progbits

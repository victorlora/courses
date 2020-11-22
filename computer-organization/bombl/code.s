
bomb:     file format elf32-i386


Disassembly of section .init:

080486c8 <_init>:
 80486c8:	55                   	push   %ebp
 80486c9:	89 e5                	mov    %esp,%ebp
 80486cb:	53                   	push   %ebx
 80486cc:	83 ec 04             	sub    $0x4,%esp
 80486cf:	e8 00 00 00 00       	call   80486d4 <_init+0xc>
 80486d4:	5b                   	pop    %ebx
 80486d5:	81 c3 0c 2a 00 00    	add    $0x2a0c,%ebx
 80486db:	8b 93 fc ff ff ff    	mov    -0x4(%ebx),%edx
 80486e1:	85 d2                	test   %edx,%edx
 80486e3:	74 05                	je     80486ea <_init+0x22>
 80486e5:	e8 5e 00 00 00       	call   8048748 <__gmon_start__@plt>
 80486ea:	e8 81 02 00 00       	call   8048970 <frame_dummy>
 80486ef:	e8 6c 18 00 00       	call   8049f60 <__do_global_ctors_aux>
 80486f4:	58                   	pop    %eax
 80486f5:	5b                   	pop    %ebx
 80486f6:	c9                   	leave  
 80486f7:	c3                   	ret    

Disassembly of section .plt:

080486f8 <__errno_location@plt-0x10>:
 80486f8:	ff 35 e4 b0 04 08    	pushl  0x804b0e4
 80486fe:	ff 25 e8 b0 04 08    	jmp    *0x804b0e8
 8048704:	00 00                	add    %al,(%eax)
	...

08048708 <__errno_location@plt>:
 8048708:	ff 25 ec b0 04 08    	jmp    *0x804b0ec
 804870e:	68 00 00 00 00       	push   $0x0
 8048713:	e9 e0 ff ff ff       	jmp    80486f8 <_init+0x30>

08048718 <sprintf@plt>:
 8048718:	ff 25 f0 b0 04 08    	jmp    *0x804b0f0
 804871e:	68 08 00 00 00       	push   $0x8
 8048723:	e9 d0 ff ff ff       	jmp    80486f8 <_init+0x30>

08048728 <connect@plt>:
 8048728:	ff 25 f4 b0 04 08    	jmp    *0x804b0f4
 804872e:	68 10 00 00 00       	push   $0x10
 8048733:	e9 c0 ff ff ff       	jmp    80486f8 <_init+0x30>

08048738 <signal@plt>:
 8048738:	ff 25 f8 b0 04 08    	jmp    *0x804b0f8
 804873e:	68 18 00 00 00       	push   $0x18
 8048743:	e9 b0 ff ff ff       	jmp    80486f8 <_init+0x30>

08048748 <__gmon_start__@plt>:
 8048748:	ff 25 fc b0 04 08    	jmp    *0x804b0fc
 804874e:	68 20 00 00 00       	push   $0x20
 8048753:	e9 a0 ff ff ff       	jmp    80486f8 <_init+0x30>

08048758 <__isoc99_sscanf@plt>:
 8048758:	ff 25 00 b1 04 08    	jmp    *0x804b100
 804875e:	68 28 00 00 00       	push   $0x28
 8048763:	e9 90 ff ff ff       	jmp    80486f8 <_init+0x30>

08048768 <getenv@plt>:
 8048768:	ff 25 04 b1 04 08    	jmp    *0x804b104
 804876e:	68 30 00 00 00       	push   $0x30
 8048773:	e9 80 ff ff ff       	jmp    80486f8 <_init+0x30>

08048778 <write@plt>:
 8048778:	ff 25 08 b1 04 08    	jmp    *0x804b108
 804877e:	68 38 00 00 00       	push   $0x38
 8048783:	e9 70 ff ff ff       	jmp    80486f8 <_init+0x30>

08048788 <fgets@plt>:
 8048788:	ff 25 0c b1 04 08    	jmp    *0x804b10c
 804878e:	68 40 00 00 00       	push   $0x40
 8048793:	e9 60 ff ff ff       	jmp    80486f8 <_init+0x30>

08048798 <__libc_start_main@plt>:
 8048798:	ff 25 10 b1 04 08    	jmp    *0x804b110
 804879e:	68 48 00 00 00       	push   $0x48
 80487a3:	e9 50 ff ff ff       	jmp    80486f8 <_init+0x30>

080487a8 <read@plt>:
 80487a8:	ff 25 14 b1 04 08    	jmp    *0x804b114
 80487ae:	68 50 00 00 00       	push   $0x50
 80487b3:	e9 40 ff ff ff       	jmp    80486f8 <_init+0x30>

080487b8 <strtol@plt>:
 80487b8:	ff 25 18 b1 04 08    	jmp    *0x804b118
 80487be:	68 58 00 00 00       	push   $0x58
 80487c3:	e9 30 ff ff ff       	jmp    80486f8 <_init+0x30>

080487c8 <fflush@plt>:
 80487c8:	ff 25 1c b1 04 08    	jmp    *0x804b11c
 80487ce:	68 60 00 00 00       	push   $0x60
 80487d3:	e9 20 ff ff ff       	jmp    80486f8 <_init+0x30>

080487d8 <socket@plt>:
 80487d8:	ff 25 20 b1 04 08    	jmp    *0x804b120
 80487de:	68 68 00 00 00       	push   $0x68
 80487e3:	e9 10 ff ff ff       	jmp    80486f8 <_init+0x30>

080487e8 <__ctype_b_loc@plt>:
 80487e8:	ff 25 24 b1 04 08    	jmp    *0x804b124
 80487ee:	68 70 00 00 00       	push   $0x70
 80487f3:	e9 00 ff ff ff       	jmp    80486f8 <_init+0x30>

080487f8 <bcopy@plt>:
 80487f8:	ff 25 28 b1 04 08    	jmp    *0x804b128
 80487fe:	68 78 00 00 00       	push   $0x78
 8048803:	e9 f0 fe ff ff       	jmp    80486f8 <_init+0x30>

08048808 <memcpy@plt>:
 8048808:	ff 25 2c b1 04 08    	jmp    *0x804b12c
 804880e:	68 80 00 00 00       	push   $0x80
 8048813:	e9 e0 fe ff ff       	jmp    80486f8 <_init+0x30>

08048818 <fopen@plt>:
 8048818:	ff 25 30 b1 04 08    	jmp    *0x804b130
 804881e:	68 88 00 00 00       	push   $0x88
 8048823:	e9 d0 fe ff ff       	jmp    80486f8 <_init+0x30>

08048828 <alarm@plt>:
 8048828:	ff 25 34 b1 04 08    	jmp    *0x804b134
 804882e:	68 90 00 00 00       	push   $0x90
 8048833:	e9 c0 fe ff ff       	jmp    80486f8 <_init+0x30>

08048838 <strcpy@plt>:
 8048838:	ff 25 38 b1 04 08    	jmp    *0x804b138
 804883e:	68 98 00 00 00       	push   $0x98
 8048843:	e9 b0 fe ff ff       	jmp    80486f8 <_init+0x30>

08048848 <printf@plt>:
 8048848:	ff 25 3c b1 04 08    	jmp    *0x804b13c
 804884e:	68 a0 00 00 00       	push   $0xa0
 8048853:	e9 a0 fe ff ff       	jmp    80486f8 <_init+0x30>

08048858 <strcasecmp@plt>:
 8048858:	ff 25 40 b1 04 08    	jmp    *0x804b140
 804885e:	68 a8 00 00 00       	push   $0xa8
 8048863:	e9 90 fe ff ff       	jmp    80486f8 <_init+0x30>

08048868 <close@plt>:
 8048868:	ff 25 44 b1 04 08    	jmp    *0x804b144
 804886e:	68 b0 00 00 00       	push   $0xb0
 8048873:	e9 80 fe ff ff       	jmp    80486f8 <_init+0x30>

08048878 <fprintf@plt>:
 8048878:	ff 25 48 b1 04 08    	jmp    *0x804b148
 804887e:	68 b8 00 00 00       	push   $0xb8
 8048883:	e9 70 fe ff ff       	jmp    80486f8 <_init+0x30>

08048888 <gethostname@plt>:
 8048888:	ff 25 4c b1 04 08    	jmp    *0x804b14c
 804888e:	68 c0 00 00 00       	push   $0xc0
 8048893:	e9 60 fe ff ff       	jmp    80486f8 <_init+0x30>

08048898 <sleep@plt>:
 8048898:	ff 25 50 b1 04 08    	jmp    *0x804b150
 804889e:	68 c8 00 00 00       	push   $0xc8
 80488a3:	e9 50 fe ff ff       	jmp    80486f8 <_init+0x30>

080488a8 <puts@plt>:
 80488a8:	ff 25 54 b1 04 08    	jmp    *0x804b154
 80488ae:	68 d0 00 00 00       	push   $0xd0
 80488b3:	e9 40 fe ff ff       	jmp    80486f8 <_init+0x30>

080488b8 <gethostbyname@plt>:
 80488b8:	ff 25 58 b1 04 08    	jmp    *0x804b158
 80488be:	68 d8 00 00 00       	push   $0xd8
 80488c3:	e9 30 fe ff ff       	jmp    80486f8 <_init+0x30>

080488c8 <exit@plt>:
 80488c8:	ff 25 5c b1 04 08    	jmp    *0x804b15c
 80488ce:	68 e0 00 00 00       	push   $0xe0
 80488d3:	e9 20 fe ff ff       	jmp    80486f8 <_init+0x30>

Disassembly of section .text:

080488e0 <_start>:
 80488e0:	31 ed                	xor    %ebp,%ebp
 80488e2:	5e                   	pop    %esi
 80488e3:	89 e1                	mov    %esp,%ecx
 80488e5:	83 e4 f0             	and    $0xfffffff0,%esp
 80488e8:	50                   	push   %eax
 80488e9:	54                   	push   %esp
 80488ea:	52                   	push   %edx
 80488eb:	68 f0 9e 04 08       	push   $0x8049ef0
 80488f0:	68 00 9f 04 08       	push   $0x8049f00
 80488f5:	51                   	push   %ecx
 80488f6:	56                   	push   %esi
 80488f7:	68 94 89 04 08       	push   $0x8048994
 80488fc:	e8 97 fe ff ff       	call   8048798 <__libc_start_main@plt>
 8048901:	f4                   	hlt    
 8048902:	90                   	nop
 8048903:	90                   	nop
 8048904:	90                   	nop
 8048905:	90                   	nop
 8048906:	90                   	nop
 8048907:	90                   	nop
 8048908:	90                   	nop
 8048909:	90                   	nop
 804890a:	90                   	nop
 804890b:	90                   	nop
 804890c:	90                   	nop
 804890d:	90                   	nop
 804890e:	90                   	nop
 804890f:	90                   	nop

08048910 <__do_global_dtors_aux>:
 8048910:	55                   	push   %ebp
 8048911:	89 e5                	mov    %esp,%ebp
 8048913:	53                   	push   %ebx
 8048914:	8d 64 24 fc          	lea    -0x4(%esp),%esp
 8048918:	80 3d c4 b8 04 08 00 	cmpb   $0x0,0x804b8c4
 804891f:	75 3e                	jne    804895f <__do_global_dtors_aux+0x4f>
 8048921:	bb 0c b0 04 08       	mov    $0x804b00c,%ebx
 8048926:	a1 c8 b8 04 08       	mov    0x804b8c8,%eax
 804892b:	81 eb 08 b0 04 08    	sub    $0x804b008,%ebx
 8048931:	c1 fb 02             	sar    $0x2,%ebx
 8048934:	83 eb 01             	sub    $0x1,%ebx
 8048937:	39 d8                	cmp    %ebx,%eax
 8048939:	73 1d                	jae    8048958 <__do_global_dtors_aux+0x48>
 804893b:	90                   	nop
 804893c:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
 8048940:	83 c0 01             	add    $0x1,%eax
 8048943:	a3 c8 b8 04 08       	mov    %eax,0x804b8c8
 8048948:	ff 14 85 08 b0 04 08 	call   *0x804b008(,%eax,4)
 804894f:	a1 c8 b8 04 08       	mov    0x804b8c8,%eax
 8048954:	39 d8                	cmp    %ebx,%eax
 8048956:	72 e8                	jb     8048940 <__do_global_dtors_aux+0x30>
 8048958:	c6 05 c4 b8 04 08 01 	movb   $0x1,0x804b8c4
 804895f:	8d 64 24 04          	lea    0x4(%esp),%esp
 8048963:	5b                   	pop    %ebx
 8048964:	5d                   	pop    %ebp
 8048965:	c3                   	ret    
 8048966:	8d 76 00             	lea    0x0(%esi),%esi
 8048969:	8d bc 27 00 00 00 00 	lea    0x0(%edi,%eiz,1),%edi

08048970 <frame_dummy>:
 8048970:	55                   	push   %ebp
 8048971:	89 e5                	mov    %esp,%ebp
 8048973:	8d 64 24 e8          	lea    -0x18(%esp),%esp
 8048977:	a1 10 b0 04 08       	mov    0x804b010,%eax
 804897c:	85 c0                	test   %eax,%eax
 804897e:	74 12                	je     8048992 <frame_dummy+0x22>
 8048980:	b8 00 00 00 00       	mov    $0x0,%eax
 8048985:	85 c0                	test   %eax,%eax
 8048987:	74 09                	je     8048992 <frame_dummy+0x22>
 8048989:	c7 04 24 10 b0 04 08 	movl   $0x804b010,(%esp)
 8048990:	ff d0                	call   *%eax
 8048992:	c9                   	leave  
 8048993:	c3                   	ret    

08048994 <main>:
 8048994:	55                   	push   %ebp
 8048995:	89 e5                	mov    %esp,%ebp
 8048997:	83 e4 f0             	and    $0xfffffff0,%esp
 804899a:	56                   	push   %esi
 804899b:	53                   	push   %ebx
 804899c:	83 ec 18             	sub    $0x18,%esp
 804899f:	8b 45 08             	mov    0x8(%ebp),%eax
 80489a2:	8b 75 0c             	mov    0xc(%ebp),%esi
 80489a5:	83 f8 01             	cmp    $0x1,%eax
 80489a8:	75 0c                	jne    80489b6 <main+0x22>
 80489aa:	a1 a4 b8 04 08       	mov    0x804b8a4,%eax
 80489af:	a3 d0 b8 04 08       	mov    %eax,0x804b8d0
 80489b4:	eb 65                	jmp    8048a1b <main+0x87>
 80489b6:	83 f8 02             	cmp    $0x2,%eax
 80489b9:	75 42                	jne    80489fd <main+0x69>
 80489bb:	8d 5e 04             	lea    0x4(%esi),%ebx
 80489be:	c7 44 24 04 cc 9f 04 	movl   $0x8049fcc,0x4(%esp)
 80489c5:	08 
 80489c6:	8b 03                	mov    (%ebx),%eax
 80489c8:	89 04 24             	mov    %eax,(%esp)
 80489cb:	e8 48 fe ff ff       	call   8048818 <fopen@plt>
 80489d0:	a3 d0 b8 04 08       	mov    %eax,0x804b8d0
 80489d5:	85 c0                	test   %eax,%eax
 80489d7:	75 42                	jne    8048a1b <main+0x87>
 80489d9:	8b 03                	mov    (%ebx),%eax
 80489db:	89 44 24 08          	mov    %eax,0x8(%esp)
 80489df:	8b 06                	mov    (%esi),%eax
 80489e1:	89 44 24 04          	mov    %eax,0x4(%esp)
 80489e5:	c7 04 24 ce 9f 04 08 	movl   $0x8049fce,(%esp)
 80489ec:	e8 57 fe ff ff       	call   8048848 <printf@plt>
 80489f1:	c7 04 24 08 00 00 00 	movl   $0x8,(%esp)
 80489f8:	e8 cb fe ff ff       	call   80488c8 <exit@plt>
 80489fd:	8b 06                	mov    (%esi),%eax
 80489ff:	89 44 24 04          	mov    %eax,0x4(%esp)
 8048a03:	c7 04 24 eb 9f 04 08 	movl   $0x8049feb,(%esp)
 8048a0a:	e8 39 fe ff ff       	call   8048848 <printf@plt>
 8048a0f:	c7 04 24 08 00 00 00 	movl   $0x8,(%esp)
 8048a16:	e8 ad fe ff ff       	call   80488c8 <exit@plt>
 8048a1b:	e8 74 09 00 00       	call   8049394 <initialize_bomb>
 8048a20:	c7 04 24 50 a0 04 08 	movl   $0x804a050,(%esp)
 8048a27:	e8 7c fe ff ff       	call   80488a8 <puts@plt>
 8048a2c:	c7 04 24 8c a0 04 08 	movl   $0x804a08c,(%esp)
 8048a33:	e8 70 fe ff ff       	call   80488a8 <puts@plt>
 8048a38:	e8 2b 08 00 00       	call   8049268 <read_line>
 8048a3d:	89 04 24             	mov    %eax,(%esp)
 8048a40:	e8 ce 04 00 00       	call   8048f13 <phase_1>
 8048a45:	e8 74 06 00 00       	call   80490be <phase_defused>
 8048a4a:	c7 04 24 b8 a0 04 08 	movl   $0x804a0b8,(%esp)
 8048a51:	e8 52 fe ff ff       	call   80488a8 <puts@plt>
 8048a56:	e8 0d 08 00 00       	call   8049268 <read_line>
 8048a5b:	89 04 24             	mov    %eax,(%esp)
 8048a5e:	e8 75 02 00 00       	call   8048cd8 <phase_2>
 8048a63:	e8 56 06 00 00       	call   80490be <phase_defused>
 8048a68:	c7 04 24 05 a0 04 08 	movl   $0x804a005,(%esp)
 8048a6f:	e8 34 fe ff ff       	call   80488a8 <puts@plt>
 8048a74:	e8 ef 07 00 00       	call   8049268 <read_line>
 8048a79:	89 04 24             	mov    %eax,(%esp)
 8048a7c:	e8 51 03 00 00       	call   8048dd2 <phase_3>
 8048a81:	e8 38 06 00 00       	call   80490be <phase_defused>
 8048a86:	c7 04 24 23 a0 04 08 	movl   $0x804a023,(%esp)
 8048a8d:	e8 16 fe ff ff       	call   80488a8 <puts@plt>
 8048a92:	e8 d1 07 00 00       	call   8049268 <read_line>
 8048a97:	89 04 24             	mov    %eax,(%esp)
 8048a9a:	e8 d6 02 00 00       	call   8048d75 <phase_4>
 8048a9f:	e8 1a 06 00 00       	call   80490be <phase_defused>
 8048aa4:	c7 04 24 e4 a0 04 08 	movl   $0x804a0e4,(%esp)
 8048aab:	e8 f8 fd ff ff       	call   80488a8 <puts@plt>
 8048ab0:	e8 b3 07 00 00       	call   8049268 <read_line>
 8048ab5:	89 04 24             	mov    %eax,(%esp)
 8048ab8:	e8 67 02 00 00       	call   8048d24 <phase_5>
 8048abd:	e8 fc 05 00 00       	call   80490be <phase_defused>
 8048ac2:	c7 04 24 32 a0 04 08 	movl   $0x804a032,(%esp)
 8048ac9:	e8 da fd ff ff       	call   80488a8 <puts@plt>
 8048ace:	e8 95 07 00 00       	call   8049268 <read_line>
 8048ad3:	89 04 24             	mov    %eax,(%esp)
 8048ad6:	e8 24 01 00 00       	call   8048bff <phase_6>
 8048adb:	e8 de 05 00 00       	call   80490be <phase_defused>
 8048ae0:	b8 00 00 00 00       	mov    $0x0,%eax
 8048ae5:	83 c4 18             	add    $0x18,%esp
 8048ae8:	5b                   	pop    %ebx
 8048ae9:	5e                   	pop    %esi
 8048aea:	89 ec                	mov    %ebp,%esp
 8048aec:	5d                   	pop    %ebp
 8048aed:	c3                   	ret    
 8048aee:	90                   	nop
 8048aef:	90                   	nop

08048af0 <func4>:
 8048af0:	55                   	push   %ebp
 8048af1:	89 e5                	mov    %esp,%ebp
 8048af3:	83 ec 28             	sub    $0x28,%esp
 8048af6:	89 5d f4             	mov    %ebx,-0xc(%ebp)
 8048af9:	89 75 f8             	mov    %esi,-0x8(%ebp)
 8048afc:	89 7d fc             	mov    %edi,-0x4(%ebp)
 8048aff:	8b 75 08             	mov    0x8(%ebp),%esi
 8048b02:	8b 5d 0c             	mov    0xc(%ebp),%ebx
 8048b05:	85 f6                	test   %esi,%esi
 8048b07:	7f 07                	jg     8048b10 <func4+0x20>
 8048b09:	bb 00 00 00 00       	mov    $0x0,%ebx
 8048b0e:	eb 29                	jmp    8048b39 <func4+0x49>
 8048b10:	83 fe 01             	cmp    $0x1,%esi
 8048b13:	74 24                	je     8048b39 <func4+0x49>
 8048b15:	89 5c 24 04          	mov    %ebx,0x4(%esp)
 8048b19:	8d 46 ff             	lea    -0x1(%esi),%eax
 8048b1c:	89 04 24             	mov    %eax,(%esp)
 8048b1f:	e8 cc ff ff ff       	call   8048af0 <func4>
 8048b24:	89 c7                	mov    %eax,%edi
 8048b26:	89 5c 24 04          	mov    %ebx,0x4(%esp)
 8048b2a:	83 ee 02             	sub    $0x2,%esi
 8048b2d:	89 34 24             	mov    %esi,(%esp)
 8048b30:	e8 bb ff ff ff       	call   8048af0 <func4>
 8048b35:	01 c7                	add    %eax,%edi
 8048b37:	01 fb                	add    %edi,%ebx
 8048b39:	89 d8                	mov    %ebx,%eax
 8048b3b:	8b 5d f4             	mov    -0xc(%ebp),%ebx
 8048b3e:	8b 75 f8             	mov    -0x8(%ebp),%esi
 8048b41:	8b 7d fc             	mov    -0x4(%ebp),%edi
 8048b44:	89 ec                	mov    %ebp,%esp
 8048b46:	5d                   	pop    %ebp
 8048b47:	c3                   	ret    

08048b48 <fun7>:
 8048b48:	55                   	push   %ebp
 8048b49:	89 e5                	mov    %esp,%ebp
 8048b4b:	53                   	push   %ebx
 8048b4c:	83 ec 14             	sub    $0x14,%esp
 8048b4f:	8b 55 08             	mov    0x8(%ebp),%edx
 8048b52:	8b 4d 0c             	mov    0xc(%ebp),%ecx
 8048b55:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8048b5a:	85 d2                	test   %edx,%edx
 8048b5c:	74 35                	je     8048b93 <fun7+0x4b>
 8048b5e:	8b 1a                	mov    (%edx),%ebx
 8048b60:	39 cb                	cmp    %ecx,%ebx
 8048b62:	7e 13                	jle    8048b77 <fun7+0x2f>
 8048b64:	89 4c 24 04          	mov    %ecx,0x4(%esp)
 8048b68:	8b 42 04             	mov    0x4(%edx),%eax
 8048b6b:	89 04 24             	mov    %eax,(%esp)
 8048b6e:	e8 d5 ff ff ff       	call   8048b48 <fun7>
 8048b73:	01 c0                	add    %eax,%eax
 8048b75:	eb 1c                	jmp    8048b93 <fun7+0x4b>
 8048b77:	b8 00 00 00 00       	mov    $0x0,%eax
 8048b7c:	39 cb                	cmp    %ecx,%ebx
 8048b7e:	74 13                	je     8048b93 <fun7+0x4b>
 8048b80:	89 4c 24 04          	mov    %ecx,0x4(%esp)
 8048b84:	8b 42 08             	mov    0x8(%edx),%eax
 8048b87:	89 04 24             	mov    %eax,(%esp)
 8048b8a:	e8 b9 ff ff ff       	call   8048b48 <fun7>
 8048b8f:	8d 44 00 01          	lea    0x1(%eax,%eax,1),%eax
 8048b93:	83 c4 14             	add    $0x14,%esp
 8048b96:	5b                   	pop    %ebx
 8048b97:	5d                   	pop    %ebp
 8048b98:	c3                   	ret    

08048b99 <secret_phase>:
 8048b99:	55                   	push   %ebp
 8048b9a:	89 e5                	mov    %esp,%ebp
 8048b9c:	53                   	push   %ebx
 8048b9d:	83 ec 14             	sub    $0x14,%esp
 8048ba0:	e8 c3 06 00 00       	call   8049268 <read_line>
 8048ba5:	c7 44 24 08 0a 00 00 	movl   $0xa,0x8(%esp)
 8048bac:	00 
 8048bad:	c7 44 24 04 00 00 00 	movl   $0x0,0x4(%esp)
 8048bb4:	00 
 8048bb5:	89 04 24             	mov    %eax,(%esp)
 8048bb8:	e8 fb fb ff ff       	call   80487b8 <strtol@plt>
 8048bbd:	89 c3                	mov    %eax,%ebx
 8048bbf:	8d 40 ff             	lea    -0x1(%eax),%eax
 8048bc2:	3d e8 03 00 00       	cmp    $0x3e8,%eax
 8048bc7:	76 05                	jbe    8048bce <secret_phase+0x35>
 8048bc9:	e8 8a 05 00 00       	call   8049158 <explode_bomb>
 8048bce:	89 5c 24 04          	mov    %ebx,0x4(%esp)
 8048bd2:	c7 04 24 90 b6 04 08 	movl   $0x804b690,(%esp)
 8048bd9:	e8 6a ff ff ff       	call   8048b48 <fun7>
 8048bde:	83 f8 07             	cmp    $0x7,%eax
 8048be1:	74 05                	je     8048be8 <secret_phase+0x4f>
 8048be3:	e8 70 05 00 00       	call   8049158 <explode_bomb>
 8048be8:	c7 04 24 08 a1 04 08 	movl   $0x804a108,(%esp)
 8048bef:	e8 b4 fc ff ff       	call   80488a8 <puts@plt>
 8048bf4:	e8 c5 04 00 00       	call   80490be <phase_defused>
 8048bf9:	83 c4 14             	add    $0x14,%esp
 8048bfc:	5b                   	pop    %ebx
 8048bfd:	5d                   	pop    %ebp
 8048bfe:	c3                   	ret    

08048bff <phase_6>:
 8048bff:	55                   	push   %ebp
 8048c00:	89 e5                	mov    %esp,%ebp
 8048c02:	57                   	push   %edi
 8048c03:	56                   	push   %esi
 8048c04:	53                   	push   %ebx
 8048c05:	83 ec 4c             	sub    $0x4c,%esp
 8048c08:	8d 45 d0             	lea    -0x30(%ebp),%eax
 8048c0b:	89 44 24 04          	mov    %eax,0x4(%esp)
 8048c0f:	8b 45 08             	mov    0x8(%ebp),%eax
 8048c12:	89 04 24             	mov    %eax,(%esp)
 8048c15:	e8 80 05 00 00       	call   804919a <read_six_numbers>
 8048c1a:	bf 00 00 00 00       	mov    $0x0,%edi
 8048c1f:	8d 75 d0             	lea    -0x30(%ebp),%esi
 8048c22:	8b 04 be             	mov    (%esi,%edi,4),%eax
 8048c25:	83 e8 01             	sub    $0x1,%eax
 8048c28:	83 f8 05             	cmp    $0x5,%eax
 8048c2b:	76 05                	jbe    8048c32 <phase_6+0x33>
 8048c2d:	e8 26 05 00 00       	call   8049158 <explode_bomb>
 8048c32:	83 c7 01             	add    $0x1,%edi
 8048c35:	83 ff 06             	cmp    $0x6,%edi
 8048c38:	74 1a                	je     8048c54 <phase_6+0x55>
 8048c3a:	89 fb                	mov    %edi,%ebx
 8048c3c:	8b 44 be fc          	mov    -0x4(%esi,%edi,4),%eax
 8048c40:	3b 04 9e             	cmp    (%esi,%ebx,4),%eax
 8048c43:	75 05                	jne    8048c4a <phase_6+0x4b>
 8048c45:	e8 0e 05 00 00       	call   8049158 <explode_bomb>
 8048c4a:	83 c3 01             	add    $0x1,%ebx
 8048c4d:	83 fb 05             	cmp    $0x5,%ebx
 8048c50:	7e ea                	jle    8048c3c <phase_6+0x3d>
 8048c52:	eb ce                	jmp    8048c22 <phase_6+0x23>
 8048c54:	bb 00 00 00 00       	mov    $0x0,%ebx
 8048c59:	8d 7d d0             	lea    -0x30(%ebp),%edi
 8048c5c:	eb 16                	jmp    8048c74 <phase_6+0x75>
 8048c5e:	8b 52 08             	mov    0x8(%edx),%edx
 8048c61:	83 c0 01             	add    $0x1,%eax
 8048c64:	39 c8                	cmp    %ecx,%eax
 8048c66:	75 f6                	jne    8048c5e <phase_6+0x5f>
 8048c68:	89 54 b5 b8          	mov    %edx,-0x48(%ebp,%esi,4)
 8048c6c:	83 c3 01             	add    $0x1,%ebx
 8048c6f:	83 fb 06             	cmp    $0x6,%ebx
 8048c72:	74 16                	je     8048c8a <phase_6+0x8b>
 8048c74:	89 de                	mov    %ebx,%esi
 8048c76:	8b 0c 9f             	mov    (%edi,%ebx,4),%ecx
 8048c79:	b8 01 00 00 00       	mov    $0x1,%eax
 8048c7e:	ba dc b5 04 08       	mov    $0x804b5dc,%edx
 8048c83:	83 f9 01             	cmp    $0x1,%ecx
 8048c86:	7f d6                	jg     8048c5e <phase_6+0x5f>
 8048c88:	eb de                	jmp    8048c68 <phase_6+0x69>
 8048c8a:	8b 5d b8             	mov    -0x48(%ebp),%ebx
 8048c8d:	8b 45 bc             	mov    -0x44(%ebp),%eax
 8048c90:	89 43 08             	mov    %eax,0x8(%ebx)
 8048c93:	8b 55 c0             	mov    -0x40(%ebp),%edx
 8048c96:	89 50 08             	mov    %edx,0x8(%eax)
 8048c99:	8b 45 c4             	mov    -0x3c(%ebp),%eax
 8048c9c:	89 42 08             	mov    %eax,0x8(%edx)
 8048c9f:	8b 55 c8             	mov    -0x38(%ebp),%edx
 8048ca2:	89 50 08             	mov    %edx,0x8(%eax)
 8048ca5:	8b 45 cc             	mov    -0x34(%ebp),%eax
 8048ca8:	89 42 08             	mov    %eax,0x8(%edx)
 8048cab:	c7 40 08 00 00 00 00 	movl   $0x0,0x8(%eax)
 8048cb2:	be 00 00 00 00       	mov    $0x0,%esi
 8048cb7:	8b 43 08             	mov    0x8(%ebx),%eax
 8048cba:	8b 13                	mov    (%ebx),%edx
 8048cbc:	3b 10                	cmp    (%eax),%edx
 8048cbe:	7e 05                	jle    8048cc5 <phase_6+0xc6>
 8048cc0:	e8 93 04 00 00       	call   8049158 <explode_bomb>
 8048cc5:	8b 5b 08             	mov    0x8(%ebx),%ebx
 8048cc8:	83 c6 01             	add    $0x1,%esi
 8048ccb:	83 fe 05             	cmp    $0x5,%esi
 8048cce:	75 e7                	jne    8048cb7 <phase_6+0xb8>
 8048cd0:	83 c4 4c             	add    $0x4c,%esp
 8048cd3:	5b                   	pop    %ebx
 8048cd4:	5e                   	pop    %esi
 8048cd5:	5f                   	pop    %edi
 8048cd6:	5d                   	pop    %ebp
 8048cd7:	c3                   	ret    

08048cd8 <phase_2>:
 8048cd8:	55                   	push   %ebp
 8048cd9:	89 e5                	mov    %esp,%ebp
 8048cdb:	56                   	push   %esi
 8048cdc:	53                   	push   %ebx
 8048cdd:	83 ec 30             	sub    $0x30,%esp
 8048ce0:	8d 45 e0             	lea    -0x20(%ebp),%eax
 8048ce3:	89 44 24 04          	mov    %eax,0x4(%esp)
 8048ce7:	8b 45 08             	mov    0x8(%ebp),%eax
 8048cea:	89 04 24             	mov    %eax,(%esp)
 8048ced:	e8 a8 04 00 00       	call   804919a <read_six_numbers>
 8048cf2:	83 7d e0 00          	cmpl   $0x0,-0x20(%ebp)
 8048cf6:	79 05                	jns    8048cfd <phase_2+0x25>
 8048cf8:	e8 5b 04 00 00       	call   8049158 <explode_bomb>
 8048cfd:	bb 01 00 00 00       	mov    $0x1,%ebx
 8048d02:	8d 75 e0             	lea    -0x20(%ebp),%esi
 8048d05:	89 d8                	mov    %ebx,%eax
 8048d07:	03 44 9e fc          	add    -0x4(%esi,%ebx,4),%eax
 8048d0b:	39 04 9e             	cmp    %eax,(%esi,%ebx,4)
 8048d0e:	74 05                	je     8048d15 <phase_2+0x3d>
 8048d10:	e8 43 04 00 00       	call   8049158 <explode_bomb>
 8048d15:	83 c3 01             	add    $0x1,%ebx
 8048d18:	83 fb 06             	cmp    $0x6,%ebx
 8048d1b:	75 e8                	jne    8048d05 <phase_2+0x2d>
 8048d1d:	83 c4 30             	add    $0x30,%esp
 8048d20:	5b                   	pop    %ebx
 8048d21:	5e                   	pop    %esi
 8048d22:	5d                   	pop    %ebp
 8048d23:	c3                   	ret    

08048d24 <phase_5>:
 8048d24:	55                   	push   %ebp
 8048d25:	89 e5                	mov    %esp,%ebp
 8048d27:	57                   	push   %edi
 8048d28:	56                   	push   %esi
 8048d29:	53                   	push   %ebx
 8048d2a:	83 ec 1c             	sub    $0x1c,%esp
 8048d2d:	8b 5d 08             	mov    0x8(%ebp),%ebx
 8048d30:	89 1c 24             	mov    %ebx,(%esp)
 8048d33:	e8 08 02 00 00       	call   8048f40 <string_length>
 8048d38:	83 f8 06             	cmp    $0x6,%eax
 8048d3b:	74 05                	je     8048d42 <phase_5+0x1e>
 8048d3d:	e8 16 04 00 00       	call   8049158 <explode_bomb>
 8048d42:	ba 00 00 00 00       	mov    $0x0,%edx
 8048d47:	b8 00 00 00 00       	mov    $0x0,%eax
 8048d4c:	b9 a0 a1 04 08       	mov    $0x804a1a0,%ecx
 8048d51:	0f be 34 03          	movsbl (%ebx,%eax,1),%esi
 8048d55:	83 e6 0f             	and    $0xf,%esi
 8048d58:	03 14 b1             	add    (%ecx,%esi,4),%edx
 8048d5b:	83 c0 01             	add    $0x1,%eax
 8048d5e:	83 f8 06             	cmp    $0x6,%eax
 8048d61:	75 ee                	jne    8048d51 <phase_5+0x2d>
 8048d63:	83 fa 2f             	cmp    $0x2f,%edx
 8048d66:	74 05                	je     8048d6d <phase_5+0x49>
 8048d68:	e8 eb 03 00 00       	call   8049158 <explode_bomb>
 8048d6d:	83 c4 1c             	add    $0x1c,%esp
 8048d70:	5b                   	pop    %ebx
 8048d71:	5e                   	pop    %esi
 8048d72:	5f                   	pop    %edi
 8048d73:	5d                   	pop    %ebp
 8048d74:	c3                   	ret    

08048d75 <phase_4>:
 8048d75:	55                   	push   %ebp
 8048d76:	89 e5                	mov    %esp,%ebp
 8048d78:	83 ec 28             	sub    $0x28,%esp
 8048d7b:	8d 45 f4             	lea    -0xc(%ebp),%eax
 8048d7e:	89 44 24 0c          	mov    %eax,0xc(%esp)
 8048d82:	8d 45 f0             	lea    -0x10(%ebp),%eax
 8048d85:	89 44 24 08          	mov    %eax,0x8(%esp)
 8048d89:	c7 44 24 04 4a a2 04 	movl   $0x804a24a,0x4(%esp)
 8048d90:	08 
 8048d91:	8b 45 08             	mov    0x8(%ebp),%eax
 8048d94:	89 04 24             	mov    %eax,(%esp)
 8048d97:	e8 bc f9 ff ff       	call   8048758 <__isoc99_sscanf@plt>
 8048d9c:	83 f8 02             	cmp    $0x2,%eax
 8048d9f:	75 0d                	jne    8048dae <phase_4+0x39>
 8048da1:	8b 45 f4             	mov    -0xc(%ebp),%eax
 8048da4:	83 f8 01             	cmp    $0x1,%eax
 8048da7:	7e 05                	jle    8048dae <phase_4+0x39>
 8048da9:	83 f8 04             	cmp    $0x4,%eax
 8048dac:	7e 05                	jle    8048db3 <phase_4+0x3e>
 8048dae:	e8 a5 03 00 00       	call   8049158 <explode_bomb>
 8048db3:	8b 45 f4             	mov    -0xc(%ebp),%eax
 8048db6:	89 44 24 04          	mov    %eax,0x4(%esp)
 8048dba:	c7 04 24 09 00 00 00 	movl   $0x9,(%esp)
 8048dc1:	e8 2a fd ff ff       	call   8048af0 <func4>
 8048dc6:	3b 45 f0             	cmp    -0x10(%ebp),%eax
 8048dc9:	74 05                	je     8048dd0 <phase_4+0x5b>
 8048dcb:	e8 88 03 00 00       	call   8049158 <explode_bomb>
 8048dd0:	c9                   	leave  
 8048dd1:	c3                   	ret    

08048dd2 <phase_3>:
 8048dd2:	55                   	push   %ebp
 8048dd3:	89 e5                	mov    %esp,%ebp
 8048dd5:	83 ec 38             	sub    $0x38,%esp
 8048dd8:	8d 45 f0             	lea    -0x10(%ebp),%eax
 8048ddb:	89 44 24 10          	mov    %eax,0x10(%esp)
 8048ddf:	8d 45 ef             	lea    -0x11(%ebp),%eax
 8048de2:	89 44 24 0c          	mov    %eax,0xc(%esp)
 8048de6:	8d 45 f4             	lea    -0xc(%ebp),%eax
 8048de9:	89 44 24 08          	mov    %eax,0x8(%esp)
 8048ded:	c7 44 24 04 5d a1 04 	movl   $0x804a15d,0x4(%esp)
 8048df4:	08 
 8048df5:	8b 45 08             	mov    0x8(%ebp),%eax
 8048df8:	89 04 24             	mov    %eax,(%esp)
 8048dfb:	e8 58 f9 ff ff       	call   8048758 <__isoc99_sscanf@plt>
 8048e00:	83 f8 02             	cmp    $0x2,%eax
 8048e03:	7f 05                	jg     8048e0a <phase_3+0x38>
 8048e05:	e8 4e 03 00 00       	call   8049158 <explode_bomb>
 8048e0a:	83 7d f4 07          	cmpl   $0x7,-0xc(%ebp)
 8048e0e:	0f 87 e2 00 00 00    	ja     8048ef6 <phase_3+0x124>
 8048e14:	8b 45 f4             	mov    -0xc(%ebp),%eax
 8048e17:	ff 24 85 80 a1 04 08 	jmp    *0x804a180(,%eax,4)
 8048e1e:	b8 71 00 00 00       	mov    $0x71,%eax
 8048e23:	81 7d f0 03 03 00 00 	cmpl   $0x303,-0x10(%ebp)
 8048e2a:	0f 84 d7 00 00 00    	je     8048f07 <phase_3+0x135>
 8048e30:	e8 23 03 00 00       	call   8049158 <explode_bomb>
 8048e35:	b8 71 00 00 00       	mov    $0x71,%eax
 8048e3a:	e9 c8 00 00 00       	jmp    8048f07 <phase_3+0x135>
 8048e3f:	b8 6d 00 00 00       	mov    $0x6d,%eax
 8048e44:	81 7d f0 38 03 00 00 	cmpl   $0x338,-0x10(%ebp)
 8048e4b:	0f 84 b6 00 00 00    	je     8048f07 <phase_3+0x135>
 8048e51:	e8 02 03 00 00       	call   8049158 <explode_bomb>
 8048e56:	b8 6d 00 00 00       	mov    $0x6d,%eax
 8048e5b:	e9 a7 00 00 00       	jmp    8048f07 <phase_3+0x135>
 8048e60:	81 7d f0 85 00 00 00 	cmpl   $0x85,-0x10(%ebp)
 8048e67:	0f 84 95 00 00 00    	je     8048f02 <phase_3+0x130>
 8048e6d:	e8 e6 02 00 00       	call   8049158 <explode_bomb>
 8048e72:	b8 63 00 00 00       	mov    $0x63,%eax
 8048e77:	e9 8b 00 00 00       	jmp    8048f07 <phase_3+0x135>
 8048e7c:	81 7d f0 78 02 00 00 	cmpl   $0x278,-0x10(%ebp)
 8048e83:	74 7d                	je     8048f02 <phase_3+0x130>
 8048e85:	e8 ce 02 00 00       	call   8049158 <explode_bomb>
 8048e8a:	b8 63 00 00 00       	mov    $0x63,%eax
 8048e8f:	eb 76                	jmp    8048f07 <phase_3+0x135>
 8048e91:	b8 65 00 00 00       	mov    $0x65,%eax
 8048e96:	81 7d f0 8f 03 00 00 	cmpl   $0x38f,-0x10(%ebp)
 8048e9d:	74 68                	je     8048f07 <phase_3+0x135>
 8048e9f:	e8 b4 02 00 00       	call   8049158 <explode_bomb>
 8048ea4:	b8 65 00 00 00       	mov    $0x65,%eax
 8048ea9:	eb 5c                	jmp    8048f07 <phase_3+0x135>
 8048eab:	b8 62 00 00 00       	mov    $0x62,%eax
 8048eb0:	81 7d f0 af 01 00 00 	cmpl   $0x1af,-0x10(%ebp)
 8048eb7:	74 4e                	je     8048f07 <phase_3+0x135>
 8048eb9:	e8 9a 02 00 00       	call   8049158 <explode_bomb>
 8048ebe:	b8 62 00 00 00       	mov    $0x62,%eax
 8048ec3:	eb 42                	jmp    8048f07 <phase_3+0x135>
 8048ec5:	b8 79 00 00 00       	mov    $0x79,%eax
 8048eca:	81 7d f0 3c 02 00 00 	cmpl   $0x23c,-0x10(%ebp)
 8048ed1:	74 34                	je     8048f07 <phase_3+0x135>
 8048ed3:	e8 80 02 00 00       	call   8049158 <explode_bomb>
 8048ed8:	b8 79 00 00 00       	mov    $0x79,%eax
 8048edd:	eb 28                	jmp    8048f07 <phase_3+0x135>
 8048edf:	b8 6b 00 00 00       	mov    $0x6b,%eax
 8048ee4:	83 7d f0 5c          	cmpl   $0x5c,-0x10(%ebp)
 8048ee8:	74 1d                	je     8048f07 <phase_3+0x135>
 8048eea:	e8 69 02 00 00       	call   8049158 <explode_bomb>
 8048eef:	b8 6b 00 00 00       	mov    $0x6b,%eax
 8048ef4:	eb 11                	jmp    8048f07 <phase_3+0x135>
 8048ef6:	e8 5d 02 00 00       	call   8049158 <explode_bomb>
 8048efb:	b8 65 00 00 00       	mov    $0x65,%eax
 8048f00:	eb 05                	jmp    8048f07 <phase_3+0x135>
 8048f02:	b8 63 00 00 00       	mov    $0x63,%eax
 8048f07:	3a 45 ef             	cmp    -0x11(%ebp),%al
 8048f0a:	74 05                	je     8048f11 <phase_3+0x13f>
 8048f0c:	e8 47 02 00 00       	call   8049158 <explode_bomb>
 8048f11:	c9                   	leave  
 8048f12:	c3                   	ret    

08048f13 <phase_1>:
 8048f13:	55                   	push   %ebp
 8048f14:	89 e5                	mov    %esp,%ebp
 8048f16:	83 ec 18             	sub    $0x18,%esp
 8048f19:	c7 44 24 04 30 a1 04 	movl   $0x804a130,0x4(%esp)
 8048f20:	08 
 8048f21:	8b 45 08             	mov    0x8(%ebp),%eax
 8048f24:	89 04 24             	mov    %eax,(%esp)
 8048f27:	e8 2f 00 00 00       	call   8048f5b <strings_not_equal>
 8048f2c:	85 c0                	test   %eax,%eax
 8048f2e:	74 05                	je     8048f35 <phase_1+0x22>
 8048f30:	e8 23 02 00 00       	call   8049158 <explode_bomb>
 8048f35:	c9                   	leave  
 8048f36:	c3                   	ret    
 8048f37:	90                   	nop
 8048f38:	90                   	nop
 8048f39:	90                   	nop
 8048f3a:	90                   	nop
 8048f3b:	90                   	nop
 8048f3c:	90                   	nop
 8048f3d:	90                   	nop
 8048f3e:	90                   	nop
 8048f3f:	90                   	nop

08048f40 <string_length>:
 8048f40:	55                   	push   %ebp
 8048f41:	89 e5                	mov    %esp,%ebp
 8048f43:	8b 55 08             	mov    0x8(%ebp),%edx
 8048f46:	b8 00 00 00 00       	mov    $0x0,%eax
 8048f4b:	80 3a 00             	cmpb   $0x0,(%edx)
 8048f4e:	74 09                	je     8048f59 <string_length+0x19>
 8048f50:	83 c0 01             	add    $0x1,%eax
 8048f53:	80 3c 02 00          	cmpb   $0x0,(%edx,%eax,1)
 8048f57:	75 f7                	jne    8048f50 <string_length+0x10>
 8048f59:	5d                   	pop    %ebp
 8048f5a:	c3                   	ret    

08048f5b <strings_not_equal>:
 8048f5b:	55                   	push   %ebp
 8048f5c:	89 e5                	mov    %esp,%ebp
 8048f5e:	57                   	push   %edi
 8048f5f:	56                   	push   %esi
 8048f60:	53                   	push   %ebx
 8048f61:	83 ec 04             	sub    $0x4,%esp
 8048f64:	8b 5d 08             	mov    0x8(%ebp),%ebx
 8048f67:	8b 75 0c             	mov    0xc(%ebp),%esi
 8048f6a:	89 1c 24             	mov    %ebx,(%esp)
 8048f6d:	e8 ce ff ff ff       	call   8048f40 <string_length>
 8048f72:	89 c7                	mov    %eax,%edi
 8048f74:	89 34 24             	mov    %esi,(%esp)
 8048f77:	e8 c4 ff ff ff       	call   8048f40 <string_length>
 8048f7c:	39 c7                	cmp    %eax,%edi
 8048f7e:	75 29                	jne    8048fa9 <strings_not_equal+0x4e>
 8048f80:	0f b6 13             	movzbl (%ebx),%edx
 8048f83:	84 d2                	test   %dl,%dl
 8048f85:	74 29                	je     8048fb0 <strings_not_equal+0x55>
 8048f87:	b8 00 00 00 00       	mov    $0x0,%eax
 8048f8c:	3a 16                	cmp    (%esi),%dl
 8048f8e:	74 0e                	je     8048f9e <strings_not_equal+0x43>
 8048f90:	eb 17                	jmp    8048fa9 <strings_not_equal+0x4e>
 8048f92:	0f b6 4c 06 01       	movzbl 0x1(%esi,%eax,1),%ecx
 8048f97:	83 c0 01             	add    $0x1,%eax
 8048f9a:	38 ca                	cmp    %cl,%dl
 8048f9c:	75 0b                	jne    8048fa9 <strings_not_equal+0x4e>
 8048f9e:	0f b6 54 03 01       	movzbl 0x1(%ebx,%eax,1),%edx
 8048fa3:	84 d2                	test   %dl,%dl
 8048fa5:	75 eb                	jne    8048f92 <strings_not_equal+0x37>
 8048fa7:	eb 07                	jmp    8048fb0 <strings_not_equal+0x55>
 8048fa9:	b8 01 00 00 00       	mov    $0x1,%eax
 8048fae:	eb 05                	jmp    8048fb5 <strings_not_equal+0x5a>
 8048fb0:	b8 00 00 00 00       	mov    $0x0,%eax
 8048fb5:	83 c4 04             	add    $0x4,%esp
 8048fb8:	5b                   	pop    %ebx
 8048fb9:	5e                   	pop    %esi
 8048fba:	5f                   	pop    %edi
 8048fbb:	5d                   	pop    %ebp
 8048fbc:	c3                   	ret    

08048fbd <initialize_bomb_solve>:
 8048fbd:	55                   	push   %ebp
 8048fbe:	89 e5                	mov    %esp,%ebp
 8048fc0:	5d                   	pop    %ebp
 8048fc1:	c3                   	ret    

08048fc2 <invalid_phase>:
 8048fc2:	55                   	push   %ebp
 8048fc3:	89 e5                	mov    %esp,%ebp
 8048fc5:	83 ec 18             	sub    $0x18,%esp
 8048fc8:	8b 45 08             	mov    0x8(%ebp),%eax
 8048fcb:	89 44 24 04          	mov    %eax,0x4(%esp)
 8048fcf:	c7 04 24 e0 a1 04 08 	movl   $0x804a1e0,(%esp)
 8048fd6:	e8 6d f8 ff ff       	call   8048848 <printf@plt>
 8048fdb:	c7 04 24 08 00 00 00 	movl   $0x8,(%esp)
 8048fe2:	e8 e1 f8 ff ff       	call   80488c8 <exit@plt>

08048fe7 <send_msg>:
 8048fe7:	55                   	push   %ebp
 8048fe8:	89 e5                	mov    %esp,%ebp
 8048fea:	57                   	push   %edi
 8048feb:	53                   	push   %ebx
 8048fec:	81 ec 20 40 00 00    	sub    $0x4020,%esp
 8048ff2:	8b 15 cc b8 04 08    	mov    0x804b8cc,%edx
 8048ff8:	8d 5c 92 fb          	lea    -0x5(%edx,%edx,4),%ebx
 8048ffc:	c1 e3 04             	shl    $0x4,%ebx
 8048fff:	81 c3 e0 b8 04 08    	add    $0x804b8e0,%ebx
 8049005:	89 df                	mov    %ebx,%edi
 8049007:	b8 00 00 00 00       	mov    $0x0,%eax
 804900c:	b9 ff ff ff ff       	mov    $0xffffffff,%ecx
 8049011:	f2 ae                	repnz scas %es:(%edi),%al
 8049013:	f7 d1                	not    %ecx
 8049015:	83 c1 63             	add    $0x63,%ecx
 8049018:	81 f9 00 20 00 00    	cmp    $0x2000,%ecx
 804901e:	76 18                	jbe    8049038 <send_msg+0x51>
 8049020:	c7 04 24 24 a3 04 08 	movl   $0x804a324,(%esp)
 8049027:	e8 1c f8 ff ff       	call   8048848 <printf@plt>
 804902c:	c7 04 24 08 00 00 00 	movl   $0x8,(%esp)
 8049033:	e8 90 f8 ff ff       	call   80488c8 <exit@plt>
 8049038:	83 7d 08 00          	cmpl   $0x0,0x8(%ebp)
 804903c:	b8 f1 a1 04 08       	mov    $0x804a1f1,%eax
 8049041:	b9 fa a1 04 08       	mov    $0x804a1fa,%ecx
 8049046:	0f 45 c1             	cmovne %ecx,%eax
 8049049:	89 5c 24 14          	mov    %ebx,0x14(%esp)
 804904d:	89 54 24 10          	mov    %edx,0x10(%esp)
 8049051:	89 44 24 0c          	mov    %eax,0xc(%esp)
 8049055:	a1 80 b1 04 08       	mov    0x804b180,%eax
 804905a:	89 44 24 08          	mov    %eax,0x8(%esp)
 804905e:	c7 44 24 04 02 a2 04 	movl   $0x804a202,0x4(%esp)
 8049065:	08 
 8049066:	8d 9d f8 df ff ff    	lea    -0x2008(%ebp),%ebx
 804906c:	89 1c 24             	mov    %ebx,(%esp)
 804906f:	e8 a4 f6 ff ff       	call   8048718 <sprintf@plt>
 8049074:	8d 85 f8 bf ff ff    	lea    -0x4008(%ebp),%eax
 804907a:	89 44 24 0c          	mov    %eax,0xc(%esp)
 804907e:	c7 44 24 08 00 00 00 	movl   $0x0,0x8(%esp)
 8049085:	00 
 8049086:	89 5c 24 04          	mov    %ebx,0x4(%esp)
 804908a:	c7 04 24 a0 b1 04 08 	movl   $0x804b1a0,(%esp)
 8049091:	e8 a5 0d 00 00       	call   8049e3b <driver_post>
 8049096:	85 c0                	test   %eax,%eax
 8049098:	79 1a                	jns    80490b4 <send_msg+0xcd>
 804909a:	8d 85 f8 bf ff ff    	lea    -0x4008(%ebp),%eax
 80490a0:	89 04 24             	mov    %eax,(%esp)
 80490a3:	e8 00 f8 ff ff       	call   80488a8 <puts@plt>
 80490a8:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 80490af:	e8 14 f8 ff ff       	call   80488c8 <exit@plt>
 80490b4:	81 c4 20 40 00 00    	add    $0x4020,%esp
 80490ba:	5b                   	pop    %ebx
 80490bb:	5f                   	pop    %edi
 80490bc:	5d                   	pop    %ebp
 80490bd:	c3                   	ret    

080490be <phase_defused>:
 80490be:	55                   	push   %ebp
 80490bf:	89 e5                	mov    %esp,%ebp
 80490c1:	81 ec 88 00 00 00    	sub    $0x88,%esp
 80490c7:	c7 04 24 01 00 00 00 	movl   $0x1,(%esp)
 80490ce:	e8 14 ff ff ff       	call   8048fe7 <send_msg>
 80490d3:	83 3d cc b8 04 08 06 	cmpl   $0x6,0x804b8cc
 80490da:	75 7a                	jne    8049156 <phase_defused+0x98>
 80490dc:	8d 45 a8             	lea    -0x58(%ebp),%eax
 80490df:	89 44 24 10          	mov    %eax,0x10(%esp)
 80490e3:	8d 45 a0             	lea    -0x60(%ebp),%eax
 80490e6:	89 44 24 0c          	mov    %eax,0xc(%esp)
 80490ea:	8d 45 a4             	lea    -0x5c(%ebp),%eax
 80490ed:	89 44 24 08          	mov    %eax,0x8(%esp)
 80490f1:	c7 44 24 04 0e a2 04 	movl   $0x804a20e,0x4(%esp)
 80490f8:	08 
 80490f9:	c7 04 24 d0 b9 04 08 	movl   $0x804b9d0,(%esp)
 8049100:	e8 53 f6 ff ff       	call   8048758 <__isoc99_sscanf@plt>
 8049105:	83 f8 03             	cmp    $0x3,%eax
 8049108:	75 34                	jne    804913e <phase_defused+0x80>
 804910a:	c7 44 24 04 17 a2 04 	movl   $0x804a217,0x4(%esp)
 8049111:	08 
 8049112:	8d 45 a8             	lea    -0x58(%ebp),%eax
 8049115:	89 04 24             	mov    %eax,(%esp)
 8049118:	e8 3e fe ff ff       	call   8048f5b <strings_not_equal>
 804911d:	85 c0                	test   %eax,%eax
 804911f:	75 1d                	jne    804913e <phase_defused+0x80>
 8049121:	c7 04 24 48 a3 04 08 	movl   $0x804a348,(%esp)
 8049128:	e8 7b f7 ff ff       	call   80488a8 <puts@plt>
 804912d:	c7 04 24 70 a3 04 08 	movl   $0x804a370,(%esp)
 8049134:	e8 6f f7 ff ff       	call   80488a8 <puts@plt>
 8049139:	e8 5b fa ff ff       	call   8048b99 <secret_phase>
 804913e:	c7 04 24 a8 a3 04 08 	movl   $0x804a3a8,(%esp)
 8049145:	e8 5e f7 ff ff       	call   80488a8 <puts@plt>
 804914a:	c7 04 24 d4 a3 04 08 	movl   $0x804a3d4,(%esp)
 8049151:	e8 52 f7 ff ff       	call   80488a8 <puts@plt>
 8049156:	c9                   	leave  
 8049157:	c3                   	ret    

08049158 <explode_bomb>:
 8049158:	55                   	push   %ebp
 8049159:	89 e5                	mov    %esp,%ebp
 804915b:	83 ec 18             	sub    $0x18,%esp
 804915e:	c7 04 24 1e a2 04 08 	movl   $0x804a21e,(%esp)
 8049165:	e8 3e f7 ff ff       	call   80488a8 <puts@plt>
 804916a:	c7 04 24 27 a2 04 08 	movl   $0x804a227,(%esp)
 8049171:	e8 32 f7 ff ff       	call   80488a8 <puts@plt>
 8049176:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 804917d:	e8 65 fe ff ff       	call   8048fe7 <send_msg>
 8049182:	c7 04 24 18 a4 04 08 	movl   $0x804a418,(%esp)
 8049189:	e8 1a f7 ff ff       	call   80488a8 <puts@plt>
 804918e:	c7 04 24 08 00 00 00 	movl   $0x8,(%esp)
 8049195:	e8 2e f7 ff ff       	call   80488c8 <exit@plt>

0804919a <read_six_numbers>:
 804919a:	55                   	push   %ebp
 804919b:	89 e5                	mov    %esp,%ebp
 804919d:	83 ec 28             	sub    $0x28,%esp
 80491a0:	8b 45 0c             	mov    0xc(%ebp),%eax
 80491a3:	8d 50 14             	lea    0x14(%eax),%edx
 80491a6:	89 54 24 1c          	mov    %edx,0x1c(%esp)
 80491aa:	8d 50 10             	lea    0x10(%eax),%edx
 80491ad:	89 54 24 18          	mov    %edx,0x18(%esp)
 80491b1:	8d 50 0c             	lea    0xc(%eax),%edx
 80491b4:	89 54 24 14          	mov    %edx,0x14(%esp)
 80491b8:	8d 50 08             	lea    0x8(%eax),%edx
 80491bb:	89 54 24 10          	mov    %edx,0x10(%esp)
 80491bf:	8d 50 04             	lea    0x4(%eax),%edx
 80491c2:	89 54 24 0c          	mov    %edx,0xc(%esp)
 80491c6:	89 44 24 08          	mov    %eax,0x8(%esp)
 80491ca:	c7 44 24 04 3e a2 04 	movl   $0x804a23e,0x4(%esp)
 80491d1:	08 
 80491d2:	8b 45 08             	mov    0x8(%ebp),%eax
 80491d5:	89 04 24             	mov    %eax,(%esp)
 80491d8:	e8 7b f5 ff ff       	call   8048758 <__isoc99_sscanf@plt>
 80491dd:	83 f8 05             	cmp    $0x5,%eax
 80491e0:	7f 05                	jg     80491e7 <read_six_numbers+0x4d>
 80491e2:	e8 71 ff ff ff       	call   8049158 <explode_bomb>
 80491e7:	c9                   	leave  
 80491e8:	c3                   	ret    

080491e9 <blank_line>:
 80491e9:	55                   	push   %ebp
 80491ea:	89 e5                	mov    %esp,%ebp
 80491ec:	56                   	push   %esi
 80491ed:	53                   	push   %ebx
 80491ee:	8b 75 08             	mov    0x8(%ebp),%esi
 80491f1:	eb 1b                	jmp    804920e <blank_line+0x25>
 80491f3:	e8 f0 f5 ff ff       	call   80487e8 <__ctype_b_loc@plt>
 80491f8:	0f be db             	movsbl %bl,%ebx
 80491fb:	8b 00                	mov    (%eax),%eax
 80491fd:	f6 44 58 01 20       	testb  $0x20,0x1(%eax,%ebx,2)
 8049202:	75 07                	jne    804920b <blank_line+0x22>
 8049204:	b8 00 00 00 00       	mov    $0x0,%eax
 8049209:	eb 0f                	jmp    804921a <blank_line+0x31>
 804920b:	83 c6 01             	add    $0x1,%esi
 804920e:	0f b6 1e             	movzbl (%esi),%ebx
 8049211:	84 db                	test   %bl,%bl
 8049213:	75 de                	jne    80491f3 <blank_line+0xa>
 8049215:	b8 01 00 00 00       	mov    $0x1,%eax
 804921a:	5b                   	pop    %ebx
 804921b:	5e                   	pop    %esi
 804921c:	5d                   	pop    %ebp
 804921d:	c3                   	ret    

0804921e <skip>:
 804921e:	55                   	push   %ebp
 804921f:	89 e5                	mov    %esp,%ebp
 8049221:	53                   	push   %ebx
 8049222:	83 ec 14             	sub    $0x14,%esp
 8049225:	a1 d0 b8 04 08       	mov    0x804b8d0,%eax
 804922a:	89 44 24 08          	mov    %eax,0x8(%esp)
 804922e:	c7 44 24 04 50 00 00 	movl   $0x50,0x4(%esp)
 8049235:	00 
 8049236:	a1 cc b8 04 08       	mov    0x804b8cc,%eax
 804923b:	8d 04 80             	lea    (%eax,%eax,4),%eax
 804923e:	c1 e0 04             	shl    $0x4,%eax
 8049241:	05 e0 b8 04 08       	add    $0x804b8e0,%eax
 8049246:	89 04 24             	mov    %eax,(%esp)
 8049249:	e8 3a f5 ff ff       	call   8048788 <fgets@plt>
 804924e:	89 c3                	mov    %eax,%ebx
 8049250:	85 c0                	test   %eax,%eax
 8049252:	74 0c                	je     8049260 <skip+0x42>
 8049254:	89 04 24             	mov    %eax,(%esp)
 8049257:	e8 8d ff ff ff       	call   80491e9 <blank_line>
 804925c:	85 c0                	test   %eax,%eax
 804925e:	75 c5                	jne    8049225 <skip+0x7>
 8049260:	89 d8                	mov    %ebx,%eax
 8049262:	83 c4 14             	add    $0x14,%esp
 8049265:	5b                   	pop    %ebx
 8049266:	5d                   	pop    %ebp
 8049267:	c3                   	ret    

08049268 <read_line>:
 8049268:	55                   	push   %ebp
 8049269:	89 e5                	mov    %esp,%ebp
 804926b:	83 ec 28             	sub    $0x28,%esp
 804926e:	89 5d f4             	mov    %ebx,-0xc(%ebp)
 8049271:	89 75 f8             	mov    %esi,-0x8(%ebp)
 8049274:	89 7d fc             	mov    %edi,-0x4(%ebp)
 8049277:	e8 a2 ff ff ff       	call   804921e <skip>
 804927c:	85 c0                	test   %eax,%eax
 804927e:	75 6c                	jne    80492ec <read_line+0x84>
 8049280:	a1 d0 b8 04 08       	mov    0x804b8d0,%eax
 8049285:	3b 05 a4 b8 04 08    	cmp    0x804b8a4,%eax
 804928b:	75 18                	jne    80492a5 <read_line+0x3d>
 804928d:	c7 04 24 50 a2 04 08 	movl   $0x804a250,(%esp)
 8049294:	e8 0f f6 ff ff       	call   80488a8 <puts@plt>
 8049299:	c7 04 24 08 00 00 00 	movl   $0x8,(%esp)
 80492a0:	e8 23 f6 ff ff       	call   80488c8 <exit@plt>
 80492a5:	c7 04 24 6e a2 04 08 	movl   $0x804a26e,(%esp)
 80492ac:	e8 b7 f4 ff ff       	call   8048768 <getenv@plt>
 80492b1:	85 c0                	test   %eax,%eax
 80492b3:	74 0c                	je     80492c1 <read_line+0x59>
 80492b5:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 80492bc:	e8 07 f6 ff ff       	call   80488c8 <exit@plt>
 80492c1:	a1 a4 b8 04 08       	mov    0x804b8a4,%eax
 80492c6:	a3 d0 b8 04 08       	mov    %eax,0x804b8d0
 80492cb:	e8 4e ff ff ff       	call   804921e <skip>
 80492d0:	85 c0                	test   %eax,%eax
 80492d2:	75 18                	jne    80492ec <read_line+0x84>
 80492d4:	c7 04 24 50 a2 04 08 	movl   $0x804a250,(%esp)
 80492db:	e8 c8 f5 ff ff       	call   80488a8 <puts@plt>
 80492e0:	c7 04 24 00 00 00 00 	movl   $0x0,(%esp)
 80492e7:	e8 dc f5 ff ff       	call   80488c8 <exit@plt>
 80492ec:	be e0 b8 04 08       	mov    $0x804b8e0,%esi
 80492f1:	a1 cc b8 04 08       	mov    0x804b8cc,%eax
 80492f6:	8d 04 80             	lea    (%eax,%eax,4),%eax
 80492f9:	c1 e0 04             	shl    $0x4,%eax
 80492fc:	8d b8 e0 b8 04 08    	lea    0x804b8e0(%eax),%edi
 8049302:	b8 00 00 00 00       	mov    $0x0,%eax
 8049307:	b9 ff ff ff ff       	mov    $0xffffffff,%ecx
 804930c:	f2 ae                	repnz scas %es:(%edi),%al
 804930e:	f7 d1                	not    %ecx
 8049310:	8d 59 ff             	lea    -0x1(%ecx),%ebx
 8049313:	83 fb 4e             	cmp    $0x4e,%ebx
 8049316:	7e 43                	jle    804935b <read_line+0xf3>
 8049318:	c7 04 24 79 a2 04 08 	movl   $0x804a279,(%esp)
 804931f:	e8 84 f5 ff ff       	call   80488a8 <puts@plt>
 8049324:	a1 cc b8 04 08       	mov    0x804b8cc,%eax
 8049329:	8d 50 01             	lea    0x1(%eax),%edx
 804932c:	89 15 cc b8 04 08    	mov    %edx,0x804b8cc
 8049332:	8d 04 80             	lea    (%eax,%eax,4),%eax
 8049335:	c1 e0 04             	shl    $0x4,%eax
 8049338:	8d 04 06             	lea    (%esi,%eax,1),%eax
 804933b:	c7 00 2a 2a 2a 74    	movl   $0x742a2a2a,(%eax)
 8049341:	c7 40 04 72 75 6e 63 	movl   $0x636e7572,0x4(%eax)
 8049348:	c7 40 08 61 74 65 64 	movl   $0x64657461,0x8(%eax)
 804934f:	c7 40 0c 2a 2a 2a 00 	movl   $0x2a2a2a,0xc(%eax)
 8049356:	e8 fd fd ff ff       	call   8049158 <explode_bomb>
 804935b:	a1 cc b8 04 08       	mov    0x804b8cc,%eax
 8049360:	8d 14 85 00 00 00 00 	lea    0x0(,%eax,4),%edx
 8049367:	8d 0c 02             	lea    (%edx,%eax,1),%ecx
 804936a:	c1 e1 04             	shl    $0x4,%ecx
 804936d:	c6 84 0b df b8 04 08 	movb   $0x0,0x804b8df(%ebx,%ecx,1)
 8049374:	00 
 8049375:	8d 48 01             	lea    0x1(%eax),%ecx
 8049378:	89 0d cc b8 04 08    	mov    %ecx,0x804b8cc
 804937e:	8d 04 02             	lea    (%edx,%eax,1),%eax
 8049381:	c1 e0 04             	shl    $0x4,%eax
 8049384:	8d 04 06             	lea    (%esi,%eax,1),%eax
 8049387:	8b 5d f4             	mov    -0xc(%ebp),%ebx
 804938a:	8b 75 f8             	mov    -0x8(%ebp),%esi
 804938d:	8b 7d fc             	mov    -0x4(%ebp),%edi
 8049390:	89 ec                	mov    %ebp,%esp
 8049392:	5d                   	pop    %ebp
 8049393:	c3                   	ret    

08049394 <initialize_bomb>:
 8049394:	55                   	push   %ebp
 8049395:	89 e5                	mov    %esp,%ebp
 8049397:	57                   	push   %edi
 8049398:	56                   	push   %esi
 8049399:	53                   	push   %ebx
 804939a:	81 ec 5c 20 00 00    	sub    $0x205c,%esp
 80493a0:	c7 44 24 04 6e 94 04 	movl   $0x804946e,0x4(%esp)
 80493a7:	08 
 80493a8:	c7 04 24 02 00 00 00 	movl   $0x2,(%esp)
 80493af:	e8 84 f3 ff ff       	call   8048738 <signal@plt>
 80493b4:	c7 44 24 04 40 00 00 	movl   $0x40,0x4(%esp)
 80493bb:	00 
 80493bc:	8d 45 a8             	lea    -0x58(%ebp),%eax
 80493bf:	89 04 24             	mov    %eax,(%esp)
 80493c2:	e8 c1 f4 ff ff       	call   8048888 <gethostname@plt>
 80493c7:	85 c0                	test   %eax,%eax
 80493c9:	75 18                	jne    80493e3 <initialize_bomb+0x4f>
 80493cb:	a1 a0 b6 04 08       	mov    0x804b6a0,%eax
 80493d0:	bb 00 00 00 00       	mov    $0x0,%ebx
 80493d5:	8d 75 a8             	lea    -0x58(%ebp),%esi
 80493d8:	bf a0 b6 04 08       	mov    $0x804b6a0,%edi
 80493dd:	85 c0                	test   %eax,%eax
 80493df:	75 1a                	jne    80493fb <initialize_bomb+0x67>
 80493e1:	eb 68                	jmp    804944b <initialize_bomb+0xb7>
 80493e3:	c7 04 24 3c a4 04 08 	movl   $0x804a43c,(%esp)
 80493ea:	e8 b9 f4 ff ff       	call   80488a8 <puts@plt>
 80493ef:	c7 04 24 08 00 00 00 	movl   $0x8,(%esp)
 80493f6:	e8 cd f4 ff ff       	call   80488c8 <exit@plt>
 80493fb:	89 74 24 04          	mov    %esi,0x4(%esp)
 80493ff:	89 04 24             	mov    %eax,(%esp)
 8049402:	e8 51 f4 ff ff       	call   8048858 <strcasecmp@plt>
 8049407:	85 c0                	test   %eax,%eax
 8049409:	74 0c                	je     8049417 <initialize_bomb+0x83>
 804940b:	83 c3 01             	add    $0x1,%ebx
 804940e:	8b 04 9f             	mov    (%edi,%ebx,4),%eax
 8049411:	85 c0                	test   %eax,%eax
 8049413:	75 e6                	jne    80493fb <initialize_bomb+0x67>
 8049415:	eb 34                	jmp    804944b <initialize_bomb+0xb7>
 8049417:	8d 85 a8 df ff ff    	lea    -0x2058(%ebp),%eax
 804941d:	89 04 24             	mov    %eax,(%esp)
 8049420:	e8 ab 00 00 00       	call   80494d0 <init_driver>
 8049425:	85 c0                	test   %eax,%eax
 8049427:	79 3a                	jns    8049463 <initialize_bomb+0xcf>
 8049429:	8d 85 a8 df ff ff    	lea    -0x2058(%ebp),%eax
 804942f:	89 44 24 04          	mov    %eax,0x4(%esp)
 8049433:	c7 04 24 94 a2 04 08 	movl   $0x804a294,(%esp)
 804943a:	e8 09 f4 ff ff       	call   8048848 <printf@plt>
 804943f:	c7 04 24 08 00 00 00 	movl   $0x8,(%esp)
 8049446:	e8 7d f4 ff ff       	call   80488c8 <exit@plt>
 804944b:	c7 04 24 74 a4 04 08 	movl   $0x804a474,(%esp)
 8049452:	e8 51 f4 ff ff       	call   80488a8 <puts@plt>
 8049457:	c7 04 24 08 00 00 00 	movl   $0x8,(%esp)
 804945e:	e8 65 f4 ff ff       	call   80488c8 <exit@plt>
 8049463:	81 c4 5c 20 00 00    	add    $0x205c,%esp
 8049469:	5b                   	pop    %ebx
 804946a:	5e                   	pop    %esi
 804946b:	5f                   	pop    %edi
 804946c:	5d                   	pop    %ebp
 804946d:	c3                   	ret    

0804946e <sig_handler>:
 804946e:	55                   	push   %ebp
 804946f:	89 e5                	mov    %esp,%ebp
 8049471:	83 ec 18             	sub    $0x18,%esp
 8049474:	c7 04 24 ac a4 04 08 	movl   $0x804a4ac,(%esp)
 804947b:	e8 28 f4 ff ff       	call   80488a8 <puts@plt>
 8049480:	c7 04 24 03 00 00 00 	movl   $0x3,(%esp)
 8049487:	e8 0c f4 ff ff       	call   8048898 <sleep@plt>
 804948c:	c7 04 24 ae a2 04 08 	movl   $0x804a2ae,(%esp)
 8049493:	e8 b0 f3 ff ff       	call   8048848 <printf@plt>
 8049498:	a1 c0 b8 04 08       	mov    0x804b8c0,%eax
 804949d:	89 04 24             	mov    %eax,(%esp)
 80494a0:	e8 23 f3 ff ff       	call   80487c8 <fflush@plt>
 80494a5:	c7 04 24 01 00 00 00 	movl   $0x1,(%esp)
 80494ac:	e8 e7 f3 ff ff       	call   8048898 <sleep@plt>
 80494b1:	c7 04 24 b6 a2 04 08 	movl   $0x804a2b6,(%esp)
 80494b8:	e8 eb f3 ff ff       	call   80488a8 <puts@plt>
 80494bd:	c7 04 24 10 00 00 00 	movl   $0x10,(%esp)
 80494c4:	e8 ff f3 ff ff       	call   80488c8 <exit@plt>
 80494c9:	90                   	nop
 80494ca:	90                   	nop
 80494cb:	90                   	nop
 80494cc:	90                   	nop
 80494cd:	90                   	nop
 80494ce:	90                   	nop
 80494cf:	90                   	nop

080494d0 <init_driver>:
 80494d0:	55                   	push   %ebp
 80494d1:	89 e5                	mov    %esp,%ebp
 80494d3:	57                   	push   %edi
 80494d4:	56                   	push   %esi
 80494d5:	53                   	push   %ebx
 80494d6:	83 ec 2c             	sub    $0x2c,%esp
 80494d9:	8b 7d 08             	mov    0x8(%ebp),%edi
 80494dc:	c7 44 24 04 01 00 00 	movl   $0x1,0x4(%esp)
 80494e3:	00 
 80494e4:	c7 04 24 0d 00 00 00 	movl   $0xd,(%esp)
 80494eb:	e8 48 f2 ff ff       	call   8048738 <signal@plt>
 80494f0:	c7 44 24 04 01 00 00 	movl   $0x1,0x4(%esp)
 80494f7:	00 
 80494f8:	c7 04 24 1d 00 00 00 	movl   $0x1d,(%esp)
 80494ff:	e8 34 f2 ff ff       	call   8048738 <signal@plt>
 8049504:	c7 44 24 04 01 00 00 	movl   $0x1,0x4(%esp)
 804950b:	00 
 804950c:	c7 04 24 1d 00 00 00 	movl   $0x1d,(%esp)
 8049513:	e8 20 f2 ff ff       	call   8048738 <signal@plt>
 8049518:	c7 44 24 08 00 00 00 	movl   $0x0,0x8(%esp)
 804951f:	00 
 8049520:	c7 44 24 04 01 00 00 	movl   $0x1,0x4(%esp)
 8049527:	00 
 8049528:	c7 04 24 02 00 00 00 	movl   $0x2,(%esp)
 804952f:	e8 a4 f2 ff ff       	call   80487d8 <socket@plt>
 8049534:	89 c6                	mov    %eax,%esi
 8049536:	85 c0                	test   %eax,%eax
 8049538:	79 4e                	jns    8049588 <init_driver+0xb8>
 804953a:	c7 07 45 72 72 6f    	movl   $0x6f727245,(%edi)
 8049540:	c7 47 04 72 3a 20 43 	movl   $0x43203a72,0x4(%edi)
 8049547:	c7 47 08 6c 69 65 6e 	movl   $0x6e65696c,0x8(%edi)
 804954e:	c7 47 0c 74 20 75 6e 	movl   $0x6e752074,0xc(%edi)
 8049555:	c7 47 10 61 62 6c 65 	movl   $0x656c6261,0x10(%edi)
 804955c:	c7 47 14 20 74 6f 20 	movl   $0x206f7420,0x14(%edi)
 8049563:	c7 47 18 63 72 65 61 	movl   $0x61657263,0x18(%edi)
 804956a:	c7 47 1c 74 65 20 73 	movl   $0x73206574,0x1c(%edi)
 8049571:	c7 47 20 6f 63 6b 65 	movl   $0x656b636f,0x20(%edi)
 8049578:	66 c7 47 24 74 00    	movw   $0x74,0x24(%edi)
 804957e:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049583:	e9 12 01 00 00       	jmp    804969a <init_driver+0x1ca>
 8049588:	c7 04 24 c5 a2 04 08 	movl   $0x804a2c5,(%esp)
 804958f:	e8 24 f3 ff ff       	call   80488b8 <gethostbyname@plt>
 8049594:	85 c0                	test   %eax,%eax
 8049596:	75 68                	jne    8049600 <init_driver+0x130>
 8049598:	c7 07 45 72 72 6f    	movl   $0x6f727245,(%edi)
 804959e:	c7 47 04 72 3a 20 44 	movl   $0x44203a72,0x4(%edi)
 80495a5:	c7 47 08 4e 53 20 69 	movl   $0x6920534e,0x8(%edi)
 80495ac:	c7 47 0c 73 20 75 6e 	movl   $0x6e752073,0xc(%edi)
 80495b3:	c7 47 10 61 62 6c 65 	movl   $0x656c6261,0x10(%edi)
 80495ba:	c7 47 14 20 74 6f 20 	movl   $0x206f7420,0x14(%edi)
 80495c1:	c7 47 18 72 65 73 6f 	movl   $0x6f736572,0x18(%edi)
 80495c8:	c7 47 1c 6c 76 65 20 	movl   $0x2065766c,0x1c(%edi)
 80495cf:	c7 47 20 73 65 72 76 	movl   $0x76726573,0x20(%edi)
 80495d6:	c7 47 24 65 72 20 61 	movl   $0x61207265,0x24(%edi)
 80495dd:	c7 47 28 64 64 72 65 	movl   $0x65726464,0x28(%edi)
 80495e4:	66 c7 47 2c 73 73    	movw   $0x7373,0x2c(%edi)
 80495ea:	c6 47 2e 00          	movb   $0x0,0x2e(%edi)
 80495ee:	89 34 24             	mov    %esi,(%esp)
 80495f1:	e8 72 f2 ff ff       	call   8048868 <close@plt>
 80495f6:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 80495fb:	e9 9a 00 00 00       	jmp    804969a <init_driver+0x1ca>
 8049600:	8d 5d d8             	lea    -0x28(%ebp),%ebx
 8049603:	c7 03 00 00 00 00    	movl   $0x0,(%ebx)
 8049609:	c7 43 04 00 00 00 00 	movl   $0x0,0x4(%ebx)
 8049610:	c7 43 08 00 00 00 00 	movl   $0x0,0x8(%ebx)
 8049617:	c7 43 0c 00 00 00 00 	movl   $0x0,0xc(%ebx)
 804961e:	66 c7 45 d8 02 00    	movw   $0x2,-0x28(%ebp)
 8049624:	8b 50 0c             	mov    0xc(%eax),%edx
 8049627:	89 54 24 08          	mov    %edx,0x8(%esp)
 804962b:	8d 55 dc             	lea    -0x24(%ebp),%edx
 804962e:	89 54 24 04          	mov    %edx,0x4(%esp)
 8049632:	8b 40 10             	mov    0x10(%eax),%eax
 8049635:	8b 00                	mov    (%eax),%eax
 8049637:	89 04 24             	mov    %eax,(%esp)
 804963a:	e8 b9 f1 ff ff       	call   80487f8 <bcopy@plt>
 804963f:	66 c7 45 da 3b 6e    	movw   $0x6e3b,-0x26(%ebp)
 8049645:	c7 44 24 08 10 00 00 	movl   $0x10,0x8(%esp)
 804964c:	00 
 804964d:	89 5c 24 04          	mov    %ebx,0x4(%esp)
 8049651:	89 34 24             	mov    %esi,(%esp)
 8049654:	e8 cf f0 ff ff       	call   8048728 <connect@plt>
 8049659:	85 c0                	test   %eax,%eax
 804965b:	79 27                	jns    8049684 <init_driver+0x1b4>
 804965d:	c7 44 24 08 c5 a2 04 	movl   $0x804a2c5,0x8(%esp)
 8049664:	08 
 8049665:	c7 44 24 04 24 a5 04 	movl   $0x804a524,0x4(%esp)
 804966c:	08 
 804966d:	89 3c 24             	mov    %edi,(%esp)
 8049670:	e8 a3 f0 ff ff       	call   8048718 <sprintf@plt>
 8049675:	89 34 24             	mov    %esi,(%esp)
 8049678:	e8 eb f1 ff ff       	call   8048868 <close@plt>
 804967d:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049682:	eb 16                	jmp    804969a <init_driver+0x1ca>
 8049684:	89 34 24             	mov    %esi,(%esp)
 8049687:	e8 dc f1 ff ff       	call   8048868 <close@plt>
 804968c:	66 c7 07 4f 4b       	movw   $0x4b4f,(%edi)
 8049691:	c6 47 02 00          	movb   $0x0,0x2(%edi)
 8049695:	b8 00 00 00 00       	mov    $0x0,%eax
 804969a:	83 c4 2c             	add    $0x2c,%esp
 804969d:	5b                   	pop    %ebx
 804969e:	5e                   	pop    %esi
 804969f:	5f                   	pop    %edi
 80496a0:	5d                   	pop    %ebp
 80496a1:	c3                   	ret    

080496a2 <init_timeout>:
 80496a2:	55                   	push   %ebp
 80496a3:	89 e5                	mov    %esp,%ebp
 80496a5:	53                   	push   %ebx
 80496a6:	83 ec 14             	sub    $0x14,%esp
 80496a9:	8b 5d 08             	mov    0x8(%ebp),%ebx
 80496ac:	85 db                	test   %ebx,%ebx
 80496ae:	74 26                	je     80496d6 <init_timeout+0x34>
 80496b0:	c7 44 24 04 c0 9e 04 	movl   $0x8049ec0,0x4(%esp)
 80496b7:	08 
 80496b8:	c7 04 24 0e 00 00 00 	movl   $0xe,(%esp)
 80496bf:	e8 74 f0 ff ff       	call   8048738 <signal@plt>
 80496c4:	85 db                	test   %ebx,%ebx
 80496c6:	b8 00 00 00 00       	mov    $0x0,%eax
 80496cb:	0f 48 d8             	cmovs  %eax,%ebx
 80496ce:	89 1c 24             	mov    %ebx,(%esp)
 80496d1:	e8 52 f1 ff ff       	call   8048828 <alarm@plt>
 80496d6:	83 c4 14             	add    $0x14,%esp
 80496d9:	5b                   	pop    %ebx
 80496da:	5d                   	pop    %ebp
 80496db:	c3                   	ret    

080496dc <rio_readlineb>:
 80496dc:	55                   	push   %ebp
 80496dd:	89 e5                	mov    %esp,%ebp
 80496df:	57                   	push   %edi
 80496e0:	56                   	push   %esi
 80496e1:	53                   	push   %ebx
 80496e2:	83 ec 3c             	sub    $0x3c,%esp
 80496e5:	89 c3                	mov    %eax,%ebx
 80496e7:	89 4d c8             	mov    %ecx,-0x38(%ebp)
 80496ea:	89 55 d0             	mov    %edx,-0x30(%ebp)
 80496ed:	83 f9 01             	cmp    $0x1,%ecx
 80496f0:	0f 86 be 00 00 00    	jbe    80497b4 <rio_readlineb+0xd8>
 80496f6:	8d 78 0c             	lea    0xc(%eax),%edi
 80496f9:	89 7d d4             	mov    %edi,-0x2c(%ebp)
 80496fc:	c7 45 cc 01 00 00 00 	movl   $0x1,-0x34(%ebp)
 8049703:	eb 3c                	jmp    8049741 <rio_readlineb+0x65>
 8049705:	c7 44 24 08 00 20 00 	movl   $0x2000,0x8(%esp)
 804970c:	00 
 804970d:	89 7c 24 04          	mov    %edi,0x4(%esp)
 8049711:	8b 03                	mov    (%ebx),%eax
 8049713:	89 04 24             	mov    %eax,(%esp)
 8049716:	e8 8d f0 ff ff       	call   80487a8 <read@plt>
 804971b:	89 43 04             	mov    %eax,0x4(%ebx)
 804971e:	85 c0                	test   %eax,%eax
 8049720:	79 11                	jns    8049733 <rio_readlineb+0x57>
 8049722:	e8 e1 ef ff ff       	call   8048708 <__errno_location@plt>
 8049727:	83 38 04             	cmpl   $0x4,(%eax)
 804972a:	74 15                	je     8049741 <rio_readlineb+0x65>
 804972c:	be ff ff ff ff       	mov    $0xffffffff,%esi
 8049731:	eb 55                	jmp    8049788 <rio_readlineb+0xac>
 8049733:	85 c0                	test   %eax,%eax
 8049735:	0f 84 91 00 00 00    	je     80497cc <rio_readlineb+0xf0>
 804973b:	8b 45 d4             	mov    -0x2c(%ebp),%eax
 804973e:	89 43 08             	mov    %eax,0x8(%ebx)
 8049741:	8b 73 04             	mov    0x4(%ebx),%esi
 8049744:	85 f6                	test   %esi,%esi
 8049746:	7e bd                	jle    8049705 <rio_readlineb+0x29>
 8049748:	85 f6                	test   %esi,%esi
 804974a:	0f 85 83 00 00 00    	jne    80497d3 <rio_readlineb+0xf7>
 8049750:	8b 43 08             	mov    0x8(%ebx),%eax
 8049753:	89 74 24 08          	mov    %esi,0x8(%esp)
 8049757:	89 44 24 04          	mov    %eax,0x4(%esp)
 804975b:	8d 55 e7             	lea    -0x19(%ebp),%edx
 804975e:	89 14 24             	mov    %edx,(%esp)
 8049761:	e8 a2 f0 ff ff       	call   8048808 <memcpy@plt>
 8049766:	01 73 08             	add    %esi,0x8(%ebx)
 8049769:	29 73 04             	sub    %esi,0x4(%ebx)
 804976c:	83 fe 01             	cmp    $0x1,%esi
 804976f:	75 17                	jne    8049788 <rio_readlineb+0xac>
 8049771:	0f b6 45 e7          	movzbl -0x19(%ebp),%eax
 8049775:	8b 55 d0             	mov    -0x30(%ebp),%edx
 8049778:	88 02                	mov    %al,(%edx)
 804977a:	83 c2 01             	add    $0x1,%edx
 804977d:	89 55 d0             	mov    %edx,-0x30(%ebp)
 8049780:	80 7d e7 0a          	cmpb   $0xa,-0x19(%ebp)
 8049784:	75 20                	jne    80497a6 <rio_readlineb+0xca>
 8049786:	eb 33                	jmp    80497bb <rio_readlineb+0xdf>
 8049788:	8b 45 cc             	mov    -0x34(%ebp),%eax
 804978b:	85 f6                	test   %esi,%esi
 804978d:	74 09                	je     8049798 <rio_readlineb+0xbc>
 804978f:	c7 45 cc ff ff ff ff 	movl   $0xffffffff,-0x34(%ebp)
 8049796:	eb 29                	jmp    80497c1 <rio_readlineb+0xe5>
 8049798:	83 f8 01             	cmp    $0x1,%eax
 804979b:	75 1e                	jne    80497bb <rio_readlineb+0xdf>
 804979d:	c7 45 cc 00 00 00 00 	movl   $0x0,-0x34(%ebp)
 80497a4:	eb 1b                	jmp    80497c1 <rio_readlineb+0xe5>
 80497a6:	83 45 cc 01          	addl   $0x1,-0x34(%ebp)
 80497aa:	8b 45 cc             	mov    -0x34(%ebp),%eax
 80497ad:	39 45 c8             	cmp    %eax,-0x38(%ebp)
 80497b0:	77 8f                	ja     8049741 <rio_readlineb+0x65>
 80497b2:	eb 07                	jmp    80497bb <rio_readlineb+0xdf>
 80497b4:	c7 45 cc 01 00 00 00 	movl   $0x1,-0x34(%ebp)
 80497bb:	8b 55 d0             	mov    -0x30(%ebp),%edx
 80497be:	c6 02 00             	movb   $0x0,(%edx)
 80497c1:	8b 45 cc             	mov    -0x34(%ebp),%eax
 80497c4:	83 c4 3c             	add    $0x3c,%esp
 80497c7:	5b                   	pop    %ebx
 80497c8:	5e                   	pop    %esi
 80497c9:	5f                   	pop    %edi
 80497ca:	5d                   	pop    %ebp
 80497cb:	c3                   	ret    
 80497cc:	be 00 00 00 00       	mov    $0x0,%esi
 80497d1:	eb b5                	jmp    8049788 <rio_readlineb+0xac>
 80497d3:	8b 43 08             	mov    0x8(%ebx),%eax
 80497d6:	0f b6 00             	movzbl (%eax),%eax
 80497d9:	88 45 e7             	mov    %al,-0x19(%ebp)
 80497dc:	83 43 08 01          	addl   $0x1,0x8(%ebx)
 80497e0:	83 6b 04 01          	subl   $0x1,0x4(%ebx)
 80497e4:	eb 8b                	jmp    8049771 <rio_readlineb+0x95>

080497e6 <submitr>:
 80497e6:	55                   	push   %ebp
 80497e7:	89 e5                	mov    %esp,%ebp
 80497e9:	57                   	push   %edi
 80497ea:	56                   	push   %esi
 80497eb:	53                   	push   %ebx
 80497ec:	81 ec 6c a0 00 00    	sub    $0xa06c,%esp
 80497f2:	c7 85 c8 7f ff ff 00 	movl   $0x0,-0x8038(%ebp)
 80497f9:	00 00 00 
 80497fc:	c7 44 24 08 00 00 00 	movl   $0x0,0x8(%esp)
 8049803:	00 
 8049804:	c7 44 24 04 01 00 00 	movl   $0x1,0x4(%esp)
 804980b:	00 
 804980c:	c7 04 24 02 00 00 00 	movl   $0x2,(%esp)
 8049813:	e8 c0 ef ff ff       	call   80487d8 <socket@plt>
 8049818:	89 85 b0 5f ff ff    	mov    %eax,-0xa050(%ebp)
 804981e:	85 c0                	test   %eax,%eax
 8049820:	79 1b                	jns    804983d <submitr+0x57>
 8049822:	b8 4c a5 04 08       	mov    $0x804a54c,%eax
 8049827:	b9 26 00 00 00       	mov    $0x26,%ecx
 804982c:	8b 7d 20             	mov    0x20(%ebp),%edi
 804982f:	89 c6                	mov    %eax,%esi
 8049831:	f3 a4                	rep movsb %ds:(%esi),%es:(%edi)
 8049833:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049838:	e9 8c 05 00 00       	jmp    8049dc9 <submitr+0x5e3>
 804983d:	8b 45 08             	mov    0x8(%ebp),%eax
 8049840:	89 04 24             	mov    %eax,(%esp)
 8049843:	e8 70 f0 ff ff       	call   80488b8 <gethostbyname@plt>
 8049848:	85 c0                	test   %eax,%eax
 804984a:	75 29                	jne    8049875 <submitr+0x8f>
 804984c:	b8 74 a5 04 08       	mov    $0x804a574,%eax
 8049851:	b9 2f 00 00 00       	mov    $0x2f,%ecx
 8049856:	8b 7d 20             	mov    0x20(%ebp),%edi
 8049859:	89 c6                	mov    %eax,%esi
 804985b:	f3 a4                	rep movsb %ds:(%esi),%es:(%edi)
 804985d:	8b 85 b0 5f ff ff    	mov    -0xa050(%ebp),%eax
 8049863:	89 04 24             	mov    %eax,(%esp)
 8049866:	e8 fd ef ff ff       	call   8048868 <close@plt>
 804986b:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049870:	e9 54 05 00 00       	jmp    8049dc9 <submitr+0x5e3>
 8049875:	8d 5d d8             	lea    -0x28(%ebp),%ebx
 8049878:	c7 03 00 00 00 00    	movl   $0x0,(%ebx)
 804987e:	c7 43 04 00 00 00 00 	movl   $0x0,0x4(%ebx)
 8049885:	c7 43 08 00 00 00 00 	movl   $0x0,0x8(%ebx)
 804988c:	c7 43 0c 00 00 00 00 	movl   $0x0,0xc(%ebx)
 8049893:	66 c7 45 d8 02 00    	movw   $0x2,-0x28(%ebp)
 8049899:	8b 50 0c             	mov    0xc(%eax),%edx
 804989c:	89 54 24 08          	mov    %edx,0x8(%esp)
 80498a0:	8d 55 dc             	lea    -0x24(%ebp),%edx
 80498a3:	89 54 24 04          	mov    %edx,0x4(%esp)
 80498a7:	8b 40 10             	mov    0x10(%eax),%eax
 80498aa:	8b 00                	mov    (%eax),%eax
 80498ac:	89 04 24             	mov    %eax,(%esp)
 80498af:	e8 44 ef ff ff       	call   80487f8 <bcopy@plt>
 80498b4:	0f b7 45 0c          	movzwl 0xc(%ebp),%eax
 80498b8:	66 c1 c8 08          	ror    $0x8,%ax
 80498bc:	66 89 45 da          	mov    %ax,-0x26(%ebp)
 80498c0:	c7 44 24 08 10 00 00 	movl   $0x10,0x8(%esp)
 80498c7:	00 
 80498c8:	89 5c 24 04          	mov    %ebx,0x4(%esp)
 80498cc:	8b 8d b0 5f ff ff    	mov    -0xa050(%ebp),%ecx
 80498d2:	89 0c 24             	mov    %ecx,(%esp)
 80498d5:	e8 4e ee ff ff       	call   8048728 <connect@plt>
 80498da:	85 c0                	test   %eax,%eax
 80498dc:	79 29                	jns    8049907 <submitr+0x121>
 80498de:	b8 a4 a5 04 08       	mov    $0x804a5a4,%eax
 80498e3:	b9 27 00 00 00       	mov    $0x27,%ecx
 80498e8:	8b 7d 20             	mov    0x20(%ebp),%edi
 80498eb:	89 c6                	mov    %eax,%esi
 80498ed:	f3 a4                	rep movsb %ds:(%esi),%es:(%edi)
 80498ef:	8b 85 b0 5f ff ff    	mov    -0xa050(%ebp),%eax
 80498f5:	89 04 24             	mov    %eax,(%esp)
 80498f8:	e8 6b ef ff ff       	call   8048868 <close@plt>
 80498fd:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049902:	e9 c2 04 00 00       	jmp    8049dc9 <submitr+0x5e3>
 8049907:	ba ff ff ff ff       	mov    $0xffffffff,%edx
 804990c:	8b 7d 1c             	mov    0x1c(%ebp),%edi
 804990f:	b8 00 00 00 00       	mov    $0x0,%eax
 8049914:	89 d1                	mov    %edx,%ecx
 8049916:	f2 ae                	repnz scas %es:(%edi),%al
 8049918:	89 cb                	mov    %ecx,%ebx
 804991a:	f7 d3                	not    %ebx
 804991c:	8b 7d 10             	mov    0x10(%ebp),%edi
 804991f:	89 d1                	mov    %edx,%ecx
 8049921:	f2 ae                	repnz scas %es:(%edi),%al
 8049923:	89 8d b4 5f ff ff    	mov    %ecx,-0xa04c(%ebp)
 8049929:	8b 7d 14             	mov    0x14(%ebp),%edi
 804992c:	89 d1                	mov    %edx,%ecx
 804992e:	f2 ae                	repnz scas %es:(%edi),%al
 8049930:	89 ce                	mov    %ecx,%esi
 8049932:	f7 d6                	not    %esi
 8049934:	8b 7d 18             	mov    0x18(%ebp),%edi
 8049937:	89 d1                	mov    %edx,%ecx
 8049939:	f2 ae                	repnz scas %es:(%edi),%al
 804993b:	2b b5 b4 5f ff ff    	sub    -0xa04c(%ebp),%esi
 8049941:	29 ce                	sub    %ecx,%esi
 8049943:	8d 44 5b fd          	lea    -0x3(%ebx,%ebx,2),%eax
 8049947:	8d 44 06 7b          	lea    0x7b(%esi,%eax,1),%eax
 804994b:	3d 00 20 00 00       	cmp    $0x2000,%eax
 8049950:	76 7c                	jbe    80499ce <submitr+0x1e8>
 8049952:	8b 75 20             	mov    0x20(%ebp),%esi
 8049955:	c7 06 45 72 72 6f    	movl   $0x6f727245,(%esi)
 804995b:	c7 46 04 72 3a 20 52 	movl   $0x52203a72,0x4(%esi)
 8049962:	c7 46 08 65 73 75 6c 	movl   $0x6c757365,0x8(%esi)
 8049969:	c7 46 0c 74 20 73 74 	movl   $0x74732074,0xc(%esi)
 8049970:	c7 46 10 72 69 6e 67 	movl   $0x676e6972,0x10(%esi)
 8049977:	c7 46 14 20 74 6f 6f 	movl   $0x6f6f7420,0x14(%esi)
 804997e:	c7 46 18 20 6c 61 72 	movl   $0x72616c20,0x18(%esi)
 8049985:	c7 46 1c 67 65 2e 20 	movl   $0x202e6567,0x1c(%esi)
 804998c:	c7 46 20 49 6e 63 72 	movl   $0x72636e49,0x20(%esi)
 8049993:	c7 46 24 65 61 73 65 	movl   $0x65736165,0x24(%esi)
 804999a:	c7 46 28 20 53 55 42 	movl   $0x42555320,0x28(%esi)
 80499a1:	c7 46 2c 4d 49 54 52 	movl   $0x5254494d,0x2c(%esi)
 80499a8:	c7 46 30 5f 4d 41 58 	movl   $0x58414d5f,0x30(%esi)
 80499af:	c7 46 34 42 55 46 00 	movl   $0x465542,0x34(%esi)
 80499b6:	8b bd b0 5f ff ff    	mov    -0xa050(%ebp),%edi
 80499bc:	89 3c 24             	mov    %edi,(%esp)
 80499bf:	e8 a4 ee ff ff       	call   8048868 <close@plt>
 80499c4:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 80499c9:	e9 fb 03 00 00       	jmp    8049dc9 <submitr+0x5e3>
 80499ce:	8d 95 cc 9f ff ff    	lea    -0x6034(%ebp),%edx
 80499d4:	b9 00 08 00 00       	mov    $0x800,%ecx
 80499d9:	b8 00 00 00 00       	mov    $0x0,%eax
 80499de:	89 d7                	mov    %edx,%edi
 80499e0:	f3 ab                	rep stos %eax,%es:(%edi)
 80499e2:	8b 7d 1c             	mov    0x1c(%ebp),%edi
 80499e5:	b9 ff ff ff ff       	mov    $0xffffffff,%ecx
 80499ea:	f2 ae                	repnz scas %es:(%edi),%al
 80499ec:	f7 d1                	not    %ecx
 80499ee:	89 cf                	mov    %ecx,%edi
 80499f0:	83 ef 01             	sub    $0x1,%edi
 80499f3:	0f 84 db 03 00 00    	je     8049dd4 <submitr+0x5ee>
 80499f9:	89 d6                	mov    %edx,%esi
 80499fb:	bb 00 00 00 00       	mov    $0x0,%ebx
 8049a00:	89 bd b4 5f ff ff    	mov    %edi,-0xa04c(%ebp)
 8049a06:	8b 7d 1c             	mov    0x1c(%ebp),%edi
 8049a09:	0f b6 04 1f          	movzbl (%edi,%ebx,1),%eax
 8049a0d:	3c 2a                	cmp    $0x2a,%al
 8049a0f:	74 29                	je     8049a3a <submitr+0x254>
 8049a11:	3c 2d                	cmp    $0x2d,%al
 8049a13:	74 25                	je     8049a3a <submitr+0x254>
 8049a15:	3c 2e                	cmp    $0x2e,%al
 8049a17:	74 21                	je     8049a3a <submitr+0x254>
 8049a19:	3c 5f                	cmp    $0x5f,%al
 8049a1b:	90                   	nop
 8049a1c:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
 8049a20:	74 18                	je     8049a3a <submitr+0x254>
 8049a22:	8d 50 d0             	lea    -0x30(%eax),%edx
 8049a25:	80 fa 09             	cmp    $0x9,%dl
 8049a28:	76 10                	jbe    8049a3a <submitr+0x254>
 8049a2a:	8d 50 bf             	lea    -0x41(%eax),%edx
 8049a2d:	80 fa 19             	cmp    $0x19,%dl
 8049a30:	76 08                	jbe    8049a3a <submitr+0x254>
 8049a32:	8d 50 9f             	lea    -0x61(%eax),%edx
 8049a35:	80 fa 19             	cmp    $0x19,%dl
 8049a38:	77 07                	ja     8049a41 <submitr+0x25b>
 8049a3a:	88 06                	mov    %al,(%esi)
 8049a3c:	83 c6 01             	add    $0x1,%esi
 8049a3f:	eb 55                	jmp    8049a96 <submitr+0x2b0>
 8049a41:	3c 20                	cmp    $0x20,%al
 8049a43:	75 08                	jne    8049a4d <submitr+0x267>
 8049a45:	c6 06 2b             	movb   $0x2b,(%esi)
 8049a48:	83 c6 01             	add    $0x1,%esi
 8049a4b:	eb 49                	jmp    8049a96 <submitr+0x2b0>
 8049a4d:	8d 50 e0             	lea    -0x20(%eax),%edx
 8049a50:	80 fa 5f             	cmp    $0x5f,%dl
 8049a53:	76 04                	jbe    8049a59 <submitr+0x273>
 8049a55:	3c 09                	cmp    $0x9,%al
 8049a57:	75 51                	jne    8049aaa <submitr+0x2c4>
 8049a59:	0f b6 c0             	movzbl %al,%eax
 8049a5c:	89 44 24 08          	mov    %eax,0x8(%esp)
 8049a60:	c7 44 24 04 e4 a4 04 	movl   $0x804a4e4,0x4(%esp)
 8049a67:	08 
 8049a68:	8d 85 c0 5f ff ff    	lea    -0xa040(%ebp),%eax
 8049a6e:	89 04 24             	mov    %eax,(%esp)
 8049a71:	e8 a2 ec ff ff       	call   8048718 <sprintf@plt>
 8049a76:	0f b6 85 c0 5f ff ff 	movzbl -0xa040(%ebp),%eax
 8049a7d:	88 06                	mov    %al,(%esi)
 8049a7f:	0f b6 85 c1 5f ff ff 	movzbl -0xa03f(%ebp),%eax
 8049a86:	88 46 01             	mov    %al,0x1(%esi)
 8049a89:	0f b6 85 c2 5f ff ff 	movzbl -0xa03e(%ebp),%eax
 8049a90:	88 46 02             	mov    %al,0x2(%esi)
 8049a93:	83 c6 03             	add    $0x3,%esi
 8049a96:	83 c3 01             	add    $0x1,%ebx
 8049a99:	3b 9d b4 5f ff ff    	cmp    -0xa04c(%ebp),%ebx
 8049a9f:	0f 85 64 ff ff ff    	jne    8049a09 <submitr+0x223>
 8049aa5:	e9 2a 03 00 00       	jmp    8049dd4 <submitr+0x5ee>
 8049aaa:	b8 cc a5 04 08       	mov    $0x804a5cc,%eax
 8049aaf:	b9 43 00 00 00       	mov    $0x43,%ecx
 8049ab4:	8b 7d 20             	mov    0x20(%ebp),%edi
 8049ab7:	89 c6                	mov    %eax,%esi
 8049ab9:	f3 a4                	rep movsb %ds:(%esi),%es:(%edi)
 8049abb:	8b 85 b0 5f ff ff    	mov    -0xa050(%ebp),%eax
 8049ac1:	89 04 24             	mov    %eax,(%esp)
 8049ac4:	e8 9f ed ff ff       	call   8048868 <close@plt>
 8049ac9:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049ace:	e9 f6 02 00 00       	jmp    8049dc9 <submitr+0x5e3>
 8049ad3:	01 c6                	add    %eax,%esi
 8049ad5:	89 5c 24 08          	mov    %ebx,0x8(%esp)
 8049ad9:	89 74 24 04          	mov    %esi,0x4(%esp)
 8049add:	89 3c 24             	mov    %edi,(%esp)
 8049ae0:	e8 93 ec ff ff       	call   8048778 <write@plt>
 8049ae5:	85 c0                	test   %eax,%eax
 8049ae7:	7f 0f                	jg     8049af8 <submitr+0x312>
 8049ae9:	e8 1a ec ff ff       	call   8048708 <__errno_location@plt>
 8049aee:	83 38 04             	cmpl   $0x4,(%eax)
 8049af1:	75 13                	jne    8049b06 <submitr+0x320>
 8049af3:	b8 00 00 00 00       	mov    $0x0,%eax
 8049af8:	29 c3                	sub    %eax,%ebx
 8049afa:	75 d7                	jne    8049ad3 <submitr+0x2ed>
 8049afc:	8b bd b4 5f ff ff    	mov    -0xa04c(%ebp),%edi
 8049b02:	85 ff                	test   %edi,%edi
 8049b04:	79 64                	jns    8049b6a <submitr+0x384>
 8049b06:	8b 7d 20             	mov    0x20(%ebp),%edi
 8049b09:	be 10 a6 04 08       	mov    $0x804a610,%esi
 8049b0e:	b8 2c 00 00 00       	mov    $0x2c,%eax
 8049b13:	f7 c7 01 00 00 00    	test   $0x1,%edi
 8049b19:	74 04                	je     8049b1f <submitr+0x339>
 8049b1b:	a4                   	movsb  %ds:(%esi),%es:(%edi)
 8049b1c:	83 e8 01             	sub    $0x1,%eax
 8049b1f:	f7 c7 02 00 00 00    	test   $0x2,%edi
 8049b25:	74 05                	je     8049b2c <submitr+0x346>
 8049b27:	66 a5                	movsw  %ds:(%esi),%es:(%edi)
 8049b29:	83 e8 02             	sub    $0x2,%eax
 8049b2c:	89 c1                	mov    %eax,%ecx
 8049b2e:	c1 e9 02             	shr    $0x2,%ecx
 8049b31:	f3 a5                	rep movsl %ds:(%esi),%es:(%edi)
 8049b33:	ba 00 00 00 00       	mov    $0x0,%edx
 8049b38:	a8 02                	test   $0x2,%al
 8049b3a:	74 0b                	je     8049b47 <submitr+0x361>
 8049b3c:	0f b7 0c 16          	movzwl (%esi,%edx,1),%ecx
 8049b40:	66 89 0c 17          	mov    %cx,(%edi,%edx,1)
 8049b44:	83 c2 02             	add    $0x2,%edx
 8049b47:	a8 01                	test   $0x1,%al
 8049b49:	74 07                	je     8049b52 <submitr+0x36c>
 8049b4b:	0f b6 04 16          	movzbl (%esi,%edx,1),%eax
 8049b4f:	88 04 17             	mov    %al,(%edi,%edx,1)
 8049b52:	8b 8d b0 5f ff ff    	mov    -0xa050(%ebp),%ecx
 8049b58:	89 0c 24             	mov    %ecx,(%esp)
 8049b5b:	e8 08 ed ff ff       	call   8048868 <close@plt>
 8049b60:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049b65:	e9 5f 02 00 00       	jmp    8049dc9 <submitr+0x5e3>
 8049b6a:	8b b5 b0 5f ff ff    	mov    -0xa050(%ebp),%esi
 8049b70:	89 b5 cc df ff ff    	mov    %esi,-0x2034(%ebp)
 8049b76:	c7 85 d0 df ff ff 00 	movl   $0x0,-0x2030(%ebp)
 8049b7d:	00 00 00 
 8049b80:	8d 85 cc df ff ff    	lea    -0x2034(%ebp),%eax
 8049b86:	8d 95 d8 df ff ff    	lea    -0x2028(%ebp),%edx
 8049b8c:	89 95 d4 df ff ff    	mov    %edx,-0x202c(%ebp)
 8049b92:	8d 95 cc bf ff ff    	lea    -0x4034(%ebp),%edx
 8049b98:	b9 00 20 00 00       	mov    $0x2000,%ecx
 8049b9d:	e8 3a fb ff ff       	call   80496dc <rio_readlineb>
 8049ba2:	85 c0                	test   %eax,%eax
 8049ba4:	7f 29                	jg     8049bcf <submitr+0x3e9>
 8049ba6:	b8 3c a6 04 08       	mov    $0x804a63c,%eax
 8049bab:	b9 36 00 00 00       	mov    $0x36,%ecx
 8049bb0:	8b 7d 20             	mov    0x20(%ebp),%edi
 8049bb3:	89 c6                	mov    %eax,%esi
 8049bb5:	f3 a4                	rep movsb %ds:(%esi),%es:(%edi)
 8049bb7:	8b 85 b0 5f ff ff    	mov    -0xa050(%ebp),%eax
 8049bbd:	89 04 24             	mov    %eax,(%esp)
 8049bc0:	e8 a3 ec ff ff       	call   8048868 <close@plt>
 8049bc5:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049bca:	e9 fa 01 00 00       	jmp    8049dc9 <submitr+0x5e3>
 8049bcf:	8d bd cc bf ff ff    	lea    -0x4034(%ebp),%edi
 8049bd5:	8d 85 c8 5f ff ff    	lea    -0xa038(%ebp),%eax
 8049bdb:	89 44 24 10          	mov    %eax,0x10(%esp)
 8049bdf:	8d 85 c8 7f ff ff    	lea    -0x8038(%ebp),%eax
 8049be5:	89 44 24 0c          	mov    %eax,0xc(%esp)
 8049be9:	8d 85 cc 7f ff ff    	lea    -0x8034(%ebp),%eax
 8049bef:	89 44 24 08          	mov    %eax,0x8(%esp)
 8049bf3:	c7 44 24 04 eb a4 04 	movl   $0x804a4eb,0x4(%esp)
 8049bfa:	08 
 8049bfb:	89 3c 24             	mov    %edi,(%esp)
 8049bfe:	e8 55 eb ff ff       	call   8048758 <__isoc99_sscanf@plt>
 8049c03:	8b 85 c8 7f ff ff    	mov    -0x8038(%ebp),%eax
 8049c09:	8d 9d cc bf ff ff    	lea    -0x4034(%ebp),%ebx
 8049c0f:	8d b5 cc df ff ff    	lea    -0x2034(%ebp),%esi
 8049c15:	3d c8 00 00 00       	cmp    $0xc8,%eax
 8049c1a:	0f 84 bd 00 00 00    	je     8049cdd <submitr+0x4f7>
 8049c20:	8d 95 c8 5f ff ff    	lea    -0xa038(%ebp),%edx
 8049c26:	89 54 24 0c          	mov    %edx,0xc(%esp)
 8049c2a:	89 44 24 08          	mov    %eax,0x8(%esp)
 8049c2e:	c7 44 24 04 74 a6 04 	movl   $0x804a674,0x4(%esp)
 8049c35:	08 
 8049c36:	8b 4d 20             	mov    0x20(%ebp),%ecx
 8049c39:	89 0c 24             	mov    %ecx,(%esp)
 8049c3c:	e8 d7 ea ff ff       	call   8048718 <sprintf@plt>
 8049c41:	8b b5 b0 5f ff ff    	mov    -0xa050(%ebp),%esi
 8049c47:	89 34 24             	mov    %esi,(%esp)
 8049c4a:	e8 19 ec ff ff       	call   8048868 <close@plt>
 8049c4f:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049c54:	e9 70 01 00 00       	jmp    8049dc9 <submitr+0x5e3>
 8049c59:	b9 00 20 00 00       	mov    $0x2000,%ecx
 8049c5e:	89 da                	mov    %ebx,%edx
 8049c60:	89 f0                	mov    %esi,%eax
 8049c62:	e8 75 fa ff ff       	call   80496dc <rio_readlineb>
 8049c67:	85 c0                	test   %eax,%eax
 8049c69:	7f 72                	jg     8049cdd <submitr+0x4f7>
 8049c6b:	8b 7d 20             	mov    0x20(%ebp),%edi
 8049c6e:	c7 07 45 72 72 6f    	movl   $0x6f727245,(%edi)
 8049c74:	c7 47 04 72 3a 20 43 	movl   $0x43203a72,0x4(%edi)
 8049c7b:	c7 47 08 6c 69 65 6e 	movl   $0x6e65696c,0x8(%edi)
 8049c82:	c7 47 0c 74 20 75 6e 	movl   $0x6e752074,0xc(%edi)
 8049c89:	c7 47 10 61 62 6c 65 	movl   $0x656c6261,0x10(%edi)
 8049c90:	c7 47 14 20 74 6f 20 	movl   $0x206f7420,0x14(%edi)
 8049c97:	c7 47 18 72 65 61 64 	movl   $0x64616572,0x18(%edi)
 8049c9e:	c7 47 1c 20 68 65 61 	movl   $0x61656820,0x1c(%edi)
 8049ca5:	c7 47 20 64 65 72 73 	movl   $0x73726564,0x20(%edi)
 8049cac:	c7 47 24 20 66 72 6f 	movl   $0x6f726620,0x24(%edi)
 8049cb3:	c7 47 28 6d 20 73 65 	movl   $0x6573206d,0x28(%edi)
 8049cba:	c7 47 2c 72 76 65 72 	movl   $0x72657672,0x2c(%edi)
 8049cc1:	c6 47 30 00          	movb   $0x0,0x30(%edi)
 8049cc5:	8b 85 b0 5f ff ff    	mov    -0xa050(%ebp),%eax
 8049ccb:	89 04 24             	mov    %eax,(%esp)
 8049cce:	e8 95 eb ff ff       	call   8048868 <close@plt>
 8049cd3:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049cd8:	e9 ec 00 00 00       	jmp    8049dc9 <submitr+0x5e3>
 8049cdd:	b8 fc a4 04 08       	mov    $0x804a4fc,%eax
 8049ce2:	0f b6 13             	movzbl (%ebx),%edx
 8049ce5:	3a 10                	cmp    (%eax),%dl
 8049ce7:	0f 85 6c ff ff ff    	jne    8049c59 <submitr+0x473>
 8049ced:	0f b6 53 01          	movzbl 0x1(%ebx),%edx
 8049cf1:	3a 50 01             	cmp    0x1(%eax),%dl
 8049cf4:	0f 85 5f ff ff ff    	jne    8049c59 <submitr+0x473>
 8049cfa:	0f b6 53 02          	movzbl 0x2(%ebx),%edx
 8049cfe:	3a 50 02             	cmp    0x2(%eax),%dl
 8049d01:	0f 85 52 ff ff ff    	jne    8049c59 <submitr+0x473>
 8049d07:	8d 95 cc bf ff ff    	lea    -0x4034(%ebp),%edx
 8049d0d:	8d 85 cc df ff ff    	lea    -0x2034(%ebp),%eax
 8049d13:	b9 00 20 00 00       	mov    $0x2000,%ecx
 8049d18:	e8 bf f9 ff ff       	call   80496dc <rio_readlineb>
 8049d1d:	85 c0                	test   %eax,%eax
 8049d1f:	7f 61                	jg     8049d82 <submitr+0x59c>
 8049d21:	8b 7d 20             	mov    0x20(%ebp),%edi
 8049d24:	be a4 a6 04 08       	mov    $0x804a6a4,%esi
 8049d29:	b8 38 00 00 00       	mov    $0x38,%eax
 8049d2e:	f7 c7 01 00 00 00    	test   $0x1,%edi
 8049d34:	74 04                	je     8049d3a <submitr+0x554>
 8049d36:	a4                   	movsb  %ds:(%esi),%es:(%edi)
 8049d37:	83 e8 01             	sub    $0x1,%eax
 8049d3a:	f7 c7 02 00 00 00    	test   $0x2,%edi
 8049d40:	74 05                	je     8049d47 <submitr+0x561>
 8049d42:	66 a5                	movsw  %ds:(%esi),%es:(%edi)
 8049d44:	83 e8 02             	sub    $0x2,%eax
 8049d47:	89 c1                	mov    %eax,%ecx
 8049d49:	c1 e9 02             	shr    $0x2,%ecx
 8049d4c:	f3 a5                	rep movsl %ds:(%esi),%es:(%edi)
 8049d4e:	ba 00 00 00 00       	mov    $0x0,%edx
 8049d53:	a8 02                	test   $0x2,%al
 8049d55:	74 0b                	je     8049d62 <submitr+0x57c>
 8049d57:	0f b7 0c 16          	movzwl (%esi,%edx,1),%ecx
 8049d5b:	66 89 0c 17          	mov    %cx,(%edi,%edx,1)
 8049d5f:	83 c2 02             	add    $0x2,%edx
 8049d62:	a8 01                	test   $0x1,%al
 8049d64:	74 07                	je     8049d6d <submitr+0x587>
 8049d66:	0f b6 04 16          	movzbl (%esi,%edx,1),%eax
 8049d6a:	88 04 17             	mov    %al,(%edi,%edx,1)
 8049d6d:	8b 8d b0 5f ff ff    	mov    -0xa050(%ebp),%ecx
 8049d73:	89 0c 24             	mov    %ecx,(%esp)
 8049d76:	e8 ed ea ff ff       	call   8048868 <close@plt>
 8049d7b:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049d80:	eb 47                	jmp    8049dc9 <submitr+0x5e3>
 8049d82:	89 7c 24 04          	mov    %edi,0x4(%esp)
 8049d86:	8b 75 20             	mov    0x20(%ebp),%esi
 8049d89:	89 34 24             	mov    %esi,(%esp)
 8049d8c:	e8 a7 ea ff ff       	call   8048838 <strcpy@plt>
 8049d91:	8b bd b0 5f ff ff    	mov    -0xa050(%ebp),%edi
 8049d97:	89 3c 24             	mov    %edi,(%esp)
 8049d9a:	e8 c9 ea ff ff       	call   8048868 <close@plt>
 8049d9f:	b8 ff a4 04 08       	mov    $0x804a4ff,%eax
 8049da4:	0f b6 0e             	movzbl (%esi),%ecx
 8049da7:	3a 08                	cmp    (%eax),%cl
 8049da9:	75 19                	jne    8049dc4 <submitr+0x5de>
 8049dab:	0f b6 4e 01          	movzbl 0x1(%esi),%ecx
 8049daf:	3a 48 01             	cmp    0x1(%eax),%cl
 8049db2:	75 10                	jne    8049dc4 <submitr+0x5de>
 8049db4:	0f b6 56 02          	movzbl 0x2(%esi),%edx
 8049db8:	3a 50 02             	cmp    0x2(%eax),%dl
 8049dbb:	75 07                	jne    8049dc4 <submitr+0x5de>
 8049dbd:	b8 00 00 00 00       	mov    $0x0,%eax
 8049dc2:	eb 05                	jmp    8049dc9 <submitr+0x5e3>
 8049dc4:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049dc9:	81 c4 6c a0 00 00    	add    $0xa06c,%esp
 8049dcf:	5b                   	pop    %ebx
 8049dd0:	5e                   	pop    %esi
 8049dd1:	5f                   	pop    %edi
 8049dd2:	5d                   	pop    %ebp
 8049dd3:	c3                   	ret    
 8049dd4:	8d 85 cc 9f ff ff    	lea    -0x6034(%ebp),%eax
 8049dda:	89 44 24 14          	mov    %eax,0x14(%esp)
 8049dde:	8b 45 18             	mov    0x18(%ebp),%eax
 8049de1:	89 44 24 10          	mov    %eax,0x10(%esp)
 8049de5:	8b 4d 14             	mov    0x14(%ebp),%ecx
 8049de8:	89 4c 24 0c          	mov    %ecx,0xc(%esp)
 8049dec:	8b 75 10             	mov    0x10(%ebp),%esi
 8049def:	89 74 24 08          	mov    %esi,0x8(%esp)
 8049df3:	c7 44 24 04 dc a6 04 	movl   $0x804a6dc,0x4(%esp)
 8049dfa:	08 
 8049dfb:	8d bd cc bf ff ff    	lea    -0x4034(%ebp),%edi
 8049e01:	89 3c 24             	mov    %edi,(%esp)
 8049e04:	e8 0f e9 ff ff       	call   8048718 <sprintf@plt>
 8049e09:	b8 00 00 00 00       	mov    $0x0,%eax
 8049e0e:	b9 ff ff ff ff       	mov    $0xffffffff,%ecx
 8049e13:	f2 ae                	repnz scas %es:(%edi),%al
 8049e15:	f7 d1                	not    %ecx
 8049e17:	89 cf                	mov    %ecx,%edi
 8049e19:	83 ef 01             	sub    $0x1,%edi
 8049e1c:	0f 84 48 fd ff ff    	je     8049b6a <submitr+0x384>
 8049e22:	89 fb                	mov    %edi,%ebx
 8049e24:	8d b5 cc bf ff ff    	lea    -0x4034(%ebp),%esi
 8049e2a:	89 bd b4 5f ff ff    	mov    %edi,-0xa04c(%ebp)
 8049e30:	8b bd b0 5f ff ff    	mov    -0xa050(%ebp),%edi
 8049e36:	e9 9a fc ff ff       	jmp    8049ad5 <submitr+0x2ef>

08049e3b <driver_post>:
 8049e3b:	55                   	push   %ebp
 8049e3c:	89 e5                	mov    %esp,%ebp
 8049e3e:	53                   	push   %ebx
 8049e3f:	83 ec 24             	sub    $0x24,%esp
 8049e42:	8b 45 08             	mov    0x8(%ebp),%eax
 8049e45:	8b 55 0c             	mov    0xc(%ebp),%edx
 8049e48:	8b 5d 14             	mov    0x14(%ebp),%ebx
 8049e4b:	83 7d 10 00          	cmpl   $0x0,0x10(%ebp)
 8049e4f:	74 20                	je     8049e71 <driver_post+0x36>
 8049e51:	89 54 24 04          	mov    %edx,0x4(%esp)
 8049e55:	c7 04 24 02 a5 04 08 	movl   $0x804a502,(%esp)
 8049e5c:	e8 e7 e9 ff ff       	call   8048848 <printf@plt>
 8049e61:	66 c7 03 4f 4b       	movw   $0x4b4f,(%ebx)
 8049e66:	c6 43 02 00          	movb   $0x0,0x2(%ebx)
 8049e6a:	b8 00 00 00 00       	mov    $0x0,%eax
 8049e6f:	eb 49                	jmp    8049eba <driver_post+0x7f>
 8049e71:	85 c0                	test   %eax,%eax
 8049e73:	74 37                	je     8049eac <driver_post+0x71>
 8049e75:	80 38 00             	cmpb   $0x0,(%eax)
 8049e78:	74 32                	je     8049eac <driver_post+0x71>
 8049e7a:	89 5c 24 18          	mov    %ebx,0x18(%esp)
 8049e7e:	89 54 24 14          	mov    %edx,0x14(%esp)
 8049e82:	c7 44 24 10 19 a5 04 	movl   $0x804a519,0x10(%esp)
 8049e89:	08 
 8049e8a:	89 44 24 0c          	mov    %eax,0xc(%esp)
 8049e8e:	c7 44 24 08 1d a5 04 	movl   $0x804a51d,0x8(%esp)
 8049e95:	08 
 8049e96:	c7 44 24 04 6e 3b 00 	movl   $0x3b6e,0x4(%esp)
 8049e9d:	00 
 8049e9e:	c7 04 24 c5 a2 04 08 	movl   $0x804a2c5,(%esp)
 8049ea5:	e8 3c f9 ff ff       	call   80497e6 <submitr>
 8049eaa:	eb 0e                	jmp    8049eba <driver_post+0x7f>
 8049eac:	66 c7 03 4f 4b       	movw   $0x4b4f,(%ebx)
 8049eb1:	c6 43 02 00          	movb   $0x0,0x2(%ebx)
 8049eb5:	b8 00 00 00 00       	mov    $0x0,%eax
 8049eba:	83 c4 24             	add    $0x24,%esp
 8049ebd:	5b                   	pop    %ebx
 8049ebe:	5d                   	pop    %ebp
 8049ebf:	c3                   	ret    

08049ec0 <sigalrm_handler>:
 8049ec0:	55                   	push   %ebp
 8049ec1:	89 e5                	mov    %esp,%ebp
 8049ec3:	83 ec 18             	sub    $0x18,%esp
 8049ec6:	c7 44 24 08 00 00 00 	movl   $0x0,0x8(%esp)
 8049ecd:	00 
 8049ece:	c7 44 24 04 28 a7 04 	movl   $0x804a728,0x4(%esp)
 8049ed5:	08 
 8049ed6:	a1 a0 b8 04 08       	mov    0x804b8a0,%eax
 8049edb:	89 04 24             	mov    %eax,(%esp)
 8049ede:	e8 95 e9 ff ff       	call   8048878 <fprintf@plt>
 8049ee3:	c7 04 24 01 00 00 00 	movl   $0x1,(%esp)
 8049eea:	e8 d9 e9 ff ff       	call   80488c8 <exit@plt>
 8049eef:	90                   	nop

08049ef0 <__libc_csu_fini>:
 8049ef0:	55                   	push   %ebp
 8049ef1:	89 e5                	mov    %esp,%ebp
 8049ef3:	5d                   	pop    %ebp
 8049ef4:	c3                   	ret    
 8049ef5:	66 66 2e 0f 1f 84 00 	data32 nopw %cs:0x0(%eax,%eax,1)
 8049efc:	00 00 00 00 

08049f00 <__libc_csu_init>:
 8049f00:	55                   	push   %ebp
 8049f01:	89 e5                	mov    %esp,%ebp
 8049f03:	57                   	push   %edi
 8049f04:	56                   	push   %esi
 8049f05:	53                   	push   %ebx
 8049f06:	e8 4f 00 00 00       	call   8049f5a <__i686.get_pc_thunk.bx>
 8049f0b:	81 c3 d5 11 00 00    	add    $0x11d5,%ebx
 8049f11:	83 ec 1c             	sub    $0x1c,%esp
 8049f14:	e8 af e7 ff ff       	call   80486c8 <_init>
 8049f19:	8d bb 20 ff ff ff    	lea    -0xe0(%ebx),%edi
 8049f1f:	8d 83 20 ff ff ff    	lea    -0xe0(%ebx),%eax
 8049f25:	29 c7                	sub    %eax,%edi
 8049f27:	c1 ff 02             	sar    $0x2,%edi
 8049f2a:	85 ff                	test   %edi,%edi
 8049f2c:	74 24                	je     8049f52 <__libc_csu_init+0x52>
 8049f2e:	31 f6                	xor    %esi,%esi
 8049f30:	8b 45 10             	mov    0x10(%ebp),%eax
 8049f33:	89 44 24 08          	mov    %eax,0x8(%esp)
 8049f37:	8b 45 0c             	mov    0xc(%ebp),%eax
 8049f3a:	89 44 24 04          	mov    %eax,0x4(%esp)
 8049f3e:	8b 45 08             	mov    0x8(%ebp),%eax
 8049f41:	89 04 24             	mov    %eax,(%esp)
 8049f44:	ff 94 b3 20 ff ff ff 	call   *-0xe0(%ebx,%esi,4)
 8049f4b:	83 c6 01             	add    $0x1,%esi
 8049f4e:	39 fe                	cmp    %edi,%esi
 8049f50:	72 de                	jb     8049f30 <__libc_csu_init+0x30>
 8049f52:	83 c4 1c             	add    $0x1c,%esp
 8049f55:	5b                   	pop    %ebx
 8049f56:	5e                   	pop    %esi
 8049f57:	5f                   	pop    %edi
 8049f58:	5d                   	pop    %ebp
 8049f59:	c3                   	ret    

08049f5a <__i686.get_pc_thunk.bx>:
 8049f5a:	8b 1c 24             	mov    (%esp),%ebx
 8049f5d:	c3                   	ret    
 8049f5e:	90                   	nop
 8049f5f:	90                   	nop

08049f60 <__do_global_ctors_aux>:
 8049f60:	55                   	push   %ebp
 8049f61:	89 e5                	mov    %esp,%ebp
 8049f63:	53                   	push   %ebx
 8049f64:	8d 64 24 fc          	lea    -0x4(%esp),%esp
 8049f68:	a1 00 b0 04 08       	mov    0x804b000,%eax
 8049f6d:	83 f8 ff             	cmp    $0xffffffff,%eax
 8049f70:	74 12                	je     8049f84 <__do_global_ctors_aux+0x24>
 8049f72:	bb 00 b0 04 08       	mov    $0x804b000,%ebx
 8049f77:	90                   	nop
 8049f78:	8d 5b fc             	lea    -0x4(%ebx),%ebx
 8049f7b:	ff d0                	call   *%eax
 8049f7d:	8b 03                	mov    (%ebx),%eax
 8049f7f:	83 f8 ff             	cmp    $0xffffffff,%eax
 8049f82:	75 f4                	jne    8049f78 <__do_global_ctors_aux+0x18>
 8049f84:	8d 64 24 04          	lea    0x4(%esp),%esp
 8049f88:	5b                   	pop    %ebx
 8049f89:	5d                   	pop    %ebp
 8049f8a:	c3                   	ret    
 8049f8b:	90                   	nop

Disassembly of section .fini:

08049f8c <_fini>:
 8049f8c:	55                   	push   %ebp
 8049f8d:	89 e5                	mov    %esp,%ebp
 8049f8f:	53                   	push   %ebx
 8049f90:	83 ec 04             	sub    $0x4,%esp
 8049f93:	e8 00 00 00 00       	call   8049f98 <_fini+0xc>
 8049f98:	5b                   	pop    %ebx
 8049f99:	81 c3 48 11 00 00    	add    $0x1148,%ebx
 8049f9f:	e8 6c e9 ff ff       	call   8048910 <__do_global_dtors_aux>
 8049fa4:	59                   	pop    %ecx
 8049fa5:	5b                   	pop    %ebx
 8049fa6:	c9                   	leave  
 8049fa7:	c3                   	ret    

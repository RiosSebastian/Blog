import Link from "next/link";

export default function Navbar() {
  return (
    <header className="border-b border-white/10">
      <div className="mx-auto flex max-w-5xl items-center justify-between px-6 py-4">
        <Link href="/" className="flex items-center gap-2">
          <span className="text-2xl font-bold text-brand-light">inkly</span>
        </Link>
        <nav className="flex gap-6 text-sm text-brand-light/80">
          <Link href="/" className="hover:text-brand-lilac">Inicio</Link>
          <Link href="/explorar" className="hover:text-brand-lilac">Explorar</Link>
        </nav>
      </div>
    </header>
  );
}
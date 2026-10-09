import Navbar from "@/components/Navbar";
import StoryCard from "@/components/StoryCard";
import { getArticles, getCategories, type Category } from "@/lib/api";

const categoryLabel: Record<string, string> = {
  FICCION: "Ficción",
  CONSPIRACION: "Conspiración",
  TERROR: "Terror",
  MISTERIO: "Misterio",
  OTROS: "Otros",
};

export default async function ExplorarPage({
  searchParams,
}: {
  searchParams: { q?: string; category?: Category };
}) {
  const [{ content: stories }, categories] = await Promise.all([
    getArticles({ q: searchParams.q, category: searchParams.category }),
    getCategories(),
  ]);

  return (
    <>
      <Navbar />
      <main className="mx-auto max-w-5xl px-6 py-12">
        <h1 className="mb-6 text-2xl font-bold">Explorar historias</h1>

        <form className="mb-8 flex flex-wrap gap-3" action="/explorar">
          <input
            type="text"
            name="q"
            placeholder="Buscar por título o contenido..."
            defaultValue={searchParams.q}
            className="flex-1 rounded-lg border border-white/10 bg-white/5 px-4 py-2 text-sm placeholder:text-brand-light/40 focus:border-brand-purple focus:outline-none"
          />
          <select
            name="category"
            defaultValue={searchParams.category ?? ""}
            className="rounded-lg border border-white/10 bg-white/5 px-4 py-2 text-sm"
          >
            <option value="">Todos los temas</option>
            {categories.map((c) => (
              <option key={c} value={c}>
                {categoryLabel[c] ?? c}
              </option>
            ))}
          </select>
          <button
            type="submit"
            className="rounded-lg bg-brand-purple px-5 py-2 text-sm font-medium hover:bg-brand-purple/80"
          >
            Buscar
          </button>
        </form>

        {stories.length === 0 ? (
          <p className="text-brand-light/60">No encontramos historias con esos filtros.</p>
        ) : (
          <div className="grid gap-6 sm:grid-cols-2 lg:grid-cols-3">
            {stories.map((s) => (
              <StoryCard key={s.id} article={s} />
            ))}
          </div>
        )}
      </main>
    </>
  );
}
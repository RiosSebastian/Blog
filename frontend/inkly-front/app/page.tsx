import Navbar from "@/components/Navbar";
import StoryCard from "@/components/StoryCard";
import { getArticles } from "@/lib/api";

export default async function HomePage() {
  const { content: stories } = await getArticles({ page: 0 });

  return (
    <>
      <Navbar />
      <main className="mx-auto max-w-5xl px-6 py-16">
        <section className="mb-16 text-center">
          <h1 className="text-4xl font-bold sm:text-5xl">
            Comparte tus ideas.
            <br />
            <span className="text-brand-lilac">Inspira al mundo.</span>
          </h1>
          <p className="mx-auto mt-4 max-w-md text-brand-light/70">
            Historias de ficción, conspiración y terror para lectores curiosos.
          </p>
        </section>

        <h2 className="mb-6 text-xl font-semibold">Últimas historias</h2>
        {stories.length === 0 ? (
          <p className="text-brand-light/60">Todavía no hay historias publicadas.</p>
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